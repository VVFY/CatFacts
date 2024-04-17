package com.example.catfact.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.domain.model.CatFact

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatFactViewer(
    list: List<CatFact>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier
    ) { page ->
        OneCatFact(
            fact = list[page],
            modifier = Modifier.fillMaxSize()
        )
    }
}