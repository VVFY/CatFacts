package com.example.catfact.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.catfact.ui.components.CatFactScroller
import com.example.coreui.navigation.Destinations
import com.example.coreui.navigation.Root

fun NavGraphBuilder.catFactsScrollerGraph(navController: NavController) {
    navigation(startDestination = Destinations.CatFact.route, route = Root.CatFact.route) {
        composable(Destinations.CatFact.route) {
            CatFactScroller(viewModel = hiltViewModel())
        }
    }
}