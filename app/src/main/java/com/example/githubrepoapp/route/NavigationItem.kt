package com.example.githubrepoapp.route

sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Details : NavigationItem(Screen.DETAILS.name)
    object Issue : NavigationItem(Screen.ISSUES.name)
}
