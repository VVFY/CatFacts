package com.example.coreui.navigation

/**
 * Unique route for nav graph
 */
sealed class Root(val route: String) {
    object CatFact : Root("cat-fact-graph")
}