package com.example.githubrepoapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubrepoapp.NavigationItem
import com.example.githubrepoapp.R
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI
import com.example.githubrepoapp.presentation.appcomponents.ErrorFullScreenUI
import com.example.githubrepoapp.presentation.appcomponents.ErrorItem
import com.example.githubrepoapp.presentation.appcomponents.LoadingItem
import com.example.githubrepoapp.presentation.appcomponents.NetworkErrorUI
import com.example.githubrepoapp.presentation.base.ErrorFullScreen
import com.example.githubrepoapp.presentation.base.ErrorItem
import com.example.githubrepoapp.presentation.base.ErrorNetwork
import com.example.githubrepoapp.presentation.base.ErrorToast

@Composable
fun DetailsScreen(navController: NavController , owner : String? , repo : String?) {

    val viewModel = hiltViewModel<DetailsViewModel>()

    owner?.let { owner -> repo?.let { repo -> viewModel.getRepositoryDetails(owner, repo) } }
    val state by viewModel.state.collectAsState()
    state.isLoading?.let { LoadingItem(loading = it) }
    if (state.data != null){
        Column {
            Text(text = state.data?.name ?: "")
            Text(text = state.data?.description ?: "")
            Text(text = state.data?.full_name ?: "")
            Text(text = state.data?.pushed_at ?: "")
            Text(text = state.data?.updated_at ?: "")
            Text(text = state.data?.open_issues.toString() ?: "")
            Text(text = state.data?.open_issues_count.toString() ?: "")
            Button(onClick = {
                navController.navigate("${NavigationItem.Issue.route}/${owner}/${repo}")
            } , modifier = Modifier
                .fillMaxWidth(50f)
                .fillMaxHeight(40f)
            ) {
                Text(text = "Issues")
            }
        }
    }
    state.errorView?.let { BaseErrorUI(it) }
}