package com.example.catfact.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CatFactScrollButton(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.LightGray,
    contentDescription: String,
    imageVector: ImageVector,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledIconButtonColors(containerColor = containerColor),
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}