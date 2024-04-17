package com.example.catfact.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.catfact.R
import com.example.catfact.ui.CatFactScrollerViewModel
import com.example.coreui.components.PlainSnackbar
import com.example.coreui.utils.resolveErrorMessage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatFactScroller(
    viewModel: CatFactScrollerViewModel
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val catFactPagerState = rememberPagerState(pageCount = { uiState.catFactList.size })
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val shouldScrollToNextEvent by remember {
        derivedStateOf { uiState.showNextFact }
    }

    if (shouldScrollToNextEvent) {
        with(catFactPagerState) {
            if (canScrollForward && !isScrollInProgress && currentPage == pageCount.minus(2)) {
                coroutineScope.launch {
                    animateScrollToPage(catFactPagerState.currentPage.plus(1))
                    viewModel.onNextFactShown()
                }
            }
        }
    }

    Box {
        Column {
            CatFactViewer(
                list = uiState.catFactList,
                pagerState = catFactPagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.general_horizontal_padding_10))
                    .wrapContentHeight(),
            ) {

                AnimatedVisibility(visible = catFactPagerState.currentPage > 0) {
                    CatFactScrollButton(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.scroll_button_size))
                            .clip(CircleShape),
                        contentDescription = stringResource(id = R.string.cd_left),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft
                    ) {
                        with(catFactPagerState) {
                            if (canScrollBackward && !isScrollInProgress) {
                                coroutineScope.launch {
                                    animateScrollToPage(currentPage.minus(1))
                                }
                            }
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.size(
                        width = dimensionResource(id = R.dimen.scroll_button_spacing),
                        height = 1.dp
                    )
                )

                AnimatedVisibility(visible = uiState.isLoading) {
                    CircularProgressIndicator()
                }

                AnimatedVisibility(visible = !uiState.isLoading) {
                    CatFactScrollButton(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.scroll_button_size))
                            .clip(CircleShape),
                        contentDescription = stringResource(id = R.string.cd_right),
                        imageVector = if (uiState.catFactList.isEmpty()) {
                            Icons.Default.Refresh
                        } else {
                            Icons.AutoMirrored.Filled.KeyboardArrowRight
                        }
                    ) {
                        with(catFactPagerState) {
                            if (!canScrollForward) {
                                viewModel.fetchCatFact()
                                return@with
                            }
                            if (canScrollForward && !isScrollInProgress) {
                                coroutineScope.launch {
                                    animateScrollToPage(currentPage.plus(1))
                                }
                            }
                        }
                    }
                }
            }
        }

        AnimatedVisibility(visible = uiState.isLoading && uiState.catFactList.isEmpty()) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red)
            )
        }

        PlainSnackbar(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            message = context.resolveErrorMessage(uiState.error),
            onSnackbarShown = { viewModel.errorShown() }
        )
    }
}