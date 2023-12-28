package com.example.githubrepoapp.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepoapp.presentation.appcomponents.BaseErrorUI
import com.example.githubrepoapp.presentation.appcomponents.EmptyScreen
import com.example.githubrepoapp.presentation.appcomponents.ErrorFullScreenUI
import com.example.githubrepoapp.presentation.appcomponents.ErrorItem
import com.example.githubrepoapp.presentation.appcomponents.LoadingItem
import com.example.githubrepoapp.presentation.appcomponents.NetworkErrorUI
import com.example.githubrepoapp.presentation.base.ErrorFullScreen
import com.example.githubrepoapp.presentation.base.ErrorItem
import com.example.githubrepoapp.presentation.base.ErrorNetwork
import com.example.githubrepoapp.presentation.base.ErrorToast
import com.example.githubrepoapp.presentation.home.component.RepositoryList

@Composable
fun HomesScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val state by viewModel.state.collectAsState()

    state.isLoading?.let { LoadingItem(loading = it) }

    if (state.data !=null){
        val result = state.data?.collectAsLazyPagingItems()
        if (result == null) EmptyScreen() else RepositoryList(navController = navController, data = result)
    }

    state.errorView?.let { BaseErrorUI(it) }

}


