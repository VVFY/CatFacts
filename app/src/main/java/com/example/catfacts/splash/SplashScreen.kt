package com.example.catfacts.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.catfacts.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    onNavigateToCatFacts: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displaySmall
        )
    }

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_START) {
                //This will work as of now because, SplashFragment is anyway popped on redirection.
                coroutineScope.launch {
                    delay(1000)
                    onNavigateToCatFacts()
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}