package com.mc7.newscomposeapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object About : Screen("about")
    object NewsDetail : Screen("home/{newsTitle}") {
        fun createRoute(newsTitle: String) = "home/$newsTitle"
    }
}
