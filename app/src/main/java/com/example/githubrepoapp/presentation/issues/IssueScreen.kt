package com.example.githubrepoapp.presentation.issues

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubrepoapp.presentation.appcomponents.AppScaffold
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI

@Composable
fun IssueScreen(navController: NavController , owner : String? , repo : String?) {

    AppScaffold(title = "Repo Issues") {
        val viewModel = hiltViewModel<IssuesViewModel>()
        repo?.let {owner?.let { owner -> viewModel.getIssues(owner, it) } }
        val state by viewModel.state.collectAsState()
        if (state.isLoading == true){
            CircularProgressIndicator()
        }
        if (state.data != null){
            Column {
                Text(text = state.data?.get(0)?.state ?: "")
                Text(text = state.data?.get(0)?.title ?: "")
                Text(text = state.data?.get(0)?.author ?: "")
                Text(text = state.data?.get(0)?.createdAt ?: "")
            }
        }
        state.errorView?.let { BaseErrorUI(it) }
    }
}