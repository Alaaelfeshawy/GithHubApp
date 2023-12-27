package com.example.githubrepoapp.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepoapp.presentation.appcomponents.EmptyScreen
import com.example.githubrepoapp.presentation.appcomponents.ErrorItem
import com.example.githubrepoapp.presentation.appcomponents.LoadingItem
import com.example.githubrepoapp.presentation.home.component.RepositoryList

@Composable
fun HomesScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()

    val state by viewModel.state.collectAsState()

    if (state.isLoading == true){
        LoadingItem()
    }
    if (state.data !=null){
        val result = state.data?.collectAsLazyPagingItems()
        result?.let { RepositoryList(navController = navController, data = it) }
    }else{
        EmptyScreen()
    }

    if (state.errorMessage != null){
        ErrorItem(message = "SOME THIN WENT WRONG ")
    }



}


