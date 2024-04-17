package com.example.coreui.components


import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun PlainSnackbar(
    modifier: Modifier = Modifier,
    message: String?,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onSnackbarShown: () -> Unit
) {
    LaunchedEffect(message) {
        message?.let {
            snackbarHostState.showSnackbar(it, "", duration = SnackbarDuration.Short)
            onSnackbarShown()
        }
    }
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier
    )
}
