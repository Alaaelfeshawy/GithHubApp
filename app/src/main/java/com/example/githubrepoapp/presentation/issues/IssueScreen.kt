package com.example.githubrepoapp.presentation.issues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubrepoapp.presentation.appcomponents.AppScaffold
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI
import com.example.githubrepoapp.presentation.appcomponents.EmptyScreen
import com.example.githubrepoapp.presentation.issues.component.CreateIssueList

@Composable
fun IssueScreen(navController: NavController , owner : String? , repo : String?) {

    AppScaffold(title = "Repo Issues") {
        innerPadding->
        Column (modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),){
            val viewModel = hiltViewModel<IssuesViewModel>()
            repo?.let {owner?.let { owner -> viewModel.getIssues(owner, it) } }
            val state by viewModel.state.collectAsState()
            if (state.isLoading == true){
                CircularProgressIndicator()
            }
            if (state.data != null){
                if (!state.data.isNullOrEmpty()){
                    CreateIssueList(state.data!!)
                }else{
                    EmptyScreen()
                }
            }
            state.errorView?.let { BaseErrorUI(it) }
        }

    }
}
