package com.example.githubrepoapp.presentation.details

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
import com.example.githubrepoapp.R
import com.example.githubrepoapp.presentation.appcomponents.AppScaffold
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI
import com.example.githubrepoapp.presentation.appcomponents.EmptyScreen
import com.example.githubrepoapp.presentation.appcomponents.LoadingItem
import com.example.githubrepoapp.presentation.details.components.DetailsComponent
import com.example.githubrepoapp.presentation.issues.IssuesViewModel

@Composable
fun DetailsScreen(navController: NavController , owner : String? , repo : String?) {

    val viewModel = hiltViewModel<DetailsViewModel>()

    LaunchedEffect(viewModel) {
        owner?.let { owner ->
            repo?.let { repo ->
                viewModel.getRepositoryDetails(owner, repo)
            }
        }
    }

    DetailsContent(navController,viewModel)

}

@Composable
private fun DetailsContent(navController: NavController , viewModel:DetailsViewModel) {
    AppScaffold(title = stringResource(R.string.repo_details_title)) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),) {

            val state by viewModel.state.collectAsState()
            state.isLoading?.let { LoadingItem(loading = it) }
            if (state.data != null){ DetailsComponent(navController , state.data!!) }else EmptyScreen()
            state.errorView?.let { BaseErrorUI(it) }
        }

    }

}