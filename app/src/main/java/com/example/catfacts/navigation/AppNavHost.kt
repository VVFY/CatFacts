package com.example.catfacts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.catfact.navigation.catFactsScrollerGraph
import com.example.catfacts.splash.SplashScreen
import com.example.coreui.navigation.Destinations
import com.example.coreui.navigation.Root

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.Splash.route) {
        composable(route = Destinations.Splash.route) {
            SplashScreen(
                onNavigateToCatFacts = {
                    navController.navigate(Root.CatFact.route) {
                        popUpTo(Destinations.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        catFactsScrollerGraph(navController)
    }
}