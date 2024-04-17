package com.example.catfacts

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.catfacts.navigation.AppNavHost

@Composable
fun CatFactAppContent() {
    val navController: NavHostController = rememberNavController()
    AppNavHost(navController)
}
