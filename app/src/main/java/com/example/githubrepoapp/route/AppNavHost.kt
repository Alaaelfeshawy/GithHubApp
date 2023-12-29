package com.example.githubrepoapp.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.githubrepoapp.presentation.details.DetailsScreen
import com.example.githubrepoapp.presentation.home.HomesScreen
import com.example.githubrepoapp.presentation.issues.IssueScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Home.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomesScreen(navController)
        }
        composable(
            NavigationItem.Details.route+"/{owner}/{repo}",
            arguments = listOf(
                navArgument("owner") { type = NavType.StringType },
                navArgument("repo") { type = NavType.StringType }
            )
        ) {backStackEntry->
            DetailsScreen( navController,
                backStackEntry.arguments?.getString("owner") ,
                backStackEntry.arguments?.getString("repo") ,)
        }
        composable(
            NavigationItem.Issue.route+ "/{owner}/{repo}",
            arguments = listOf(
                navArgument("owner") { type = NavType.StringType },
                navArgument("repo") { type = NavType.StringType }
            )) { backStackEntry->
            IssueScreen(navController,
                backStackEntry.arguments?.getString("owner") ,
                backStackEntry.arguments?.getString("repo") ,
            )
        }

    }
}