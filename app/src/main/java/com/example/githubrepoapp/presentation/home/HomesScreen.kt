package com.example.githubrepoapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepoapp.R
import com.example.githubrepoapp.presentation.appcomponents.AppScaffold
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI
import com.example.githubrepoapp.presentation.appcomponents.EmptyScreen
import com.example.githubrepoapp.presentation.appcomponents.LoadingItem
import com.example.githubrepoapp.presentation.home.component.RepositoryList

@Composable
fun HomesScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    LaunchedEffect(Unit) {
        viewModel.getRepositories() // Perform side effect in LaunchedEffect
    }
    HomeContent(navController, viewModel)

}

@Composable
private fun HomeContent(navController: NavController , viewModel : HomeViewModel ) {
    AppScaffold(title = stringResource(R.string.home_title)) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val state by viewModel.state.collectAsState()

            state.isLoading?.let { LoadingItem(loading = it) }

            if (state.data !=null){
                val result = state.data?.collectAsLazyPagingItems()
                if (result == null) EmptyScreen() else RepositoryList(navController = navController, data = result)
            }

            state.errorView?.let { BaseErrorUI(it) }
        }
    }

}
