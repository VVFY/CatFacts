package com.example.catfact.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.catfact.R
import com.example.domain.model.CatFact

@Composable
fun OneCatFact(
    fact: CatFact,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = fact.fact,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = dimensionResource(id = R.dimen.fact_side_spacing),
                    vertical = dimensionResource(id = R.dimen.general_vertical_padding_10),
                )
        )
    }
}

@Preview
@Composable
fun OneCatFactPreview() {
    OneCatFact(
        fact = CatFact(id = 1, fact = "Cats must have fat in their diet because they can't produce it on their own."),
        modifier = Modifier.fillMaxSize()
    )
}