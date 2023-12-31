package com.example.githubrepoapp.presentation.issues

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.githubrepoapp.presentation.issues.component.CreateIssueList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IssueScreen(navController: NavController , owner : String? , repo : String?) {

    val viewModel = hiltViewModel<IssuesViewModel>()
    LaunchedEffect(viewModel) {
        owner?.let { owner ->
            repo?.let { repo ->
                viewModel.getIssues(owner, repo)
            }
        }
    }
    IssueContent(navController, viewModel)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun IssueContent(navController: NavController , viewModel :IssuesViewModel ) {
    AppScaffold(title = stringResource(R.string.repo_issues_TITLE)) {
            innerPadding->
        Column (modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),){
            val state by viewModel.state.collectAsState()
            state.isLoading?.let { LoadingItem(loading = it) }
            if (!state.data.isNullOrEmpty()){ CreateIssueList(state.data!!) }else{ EmptyScreen() }
            state.errorView?.let { BaseErrorUI(it) }
        }

    }

}