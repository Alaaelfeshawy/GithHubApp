package com.example.githubrepoapp.presentation.issues

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubrepoapp.NavigationItem
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI
import com.example.githubrepoapp.presentation.appcomponents.ErrorFullScreenUI
import com.example.githubrepoapp.presentation.appcomponents.ErrorItem
import com.example.githubrepoapp.presentation.appcomponents.NetworkErrorUI
import com.example.githubrepoapp.presentation.base.ErrorFullScreen
import com.example.githubrepoapp.presentation.base.ErrorItem
import com.example.githubrepoapp.presentation.base.ErrorNetwork
import com.example.githubrepoapp.presentation.base.ErrorToast

@Composable
fun IssueScreen(navController: NavController , owner : String? , repo : String?) {

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