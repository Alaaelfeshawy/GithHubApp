package com.example.githubrepoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubrepoapp.presentation.details.DetailsScreen
import com.example.githubrepoapp.presentation.home.HomeViewModel
import com.example.githubrepoapp.presentation.home.HomesScreen
import com.example.githubrepoapp.presentation.issues.IssueScreen
import com.example.githubrepoapp.ui.theme.GitHubRepoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubRepoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}

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
        composable(NavigationItem.Issue.route+ "/{owner}/{repo}",
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

enum class Screen {
    HOME,
    DETAILS,
    ISSUES
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Details : NavigationItem(Screen.DETAILS.name)
    object Issue : NavigationItem(Screen.ISSUES.name)
}