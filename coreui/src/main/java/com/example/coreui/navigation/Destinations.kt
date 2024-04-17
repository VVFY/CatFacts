package com.example.coreui.navigation

/**
 * Unique route for destinations defined in nav graphs.
 * For unique routes to graph, visit [Root]
 */
sealed class Destinations(val route: String, val name: String) {
    object Splash : Destinations("splash", "Start")
    object CatFact : Destinations("catfact", "catfacts")
}