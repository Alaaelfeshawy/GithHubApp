package com.example.githubrepoapp.presentation.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubrepoapp.R
import com.example.githubrepoapp.data.network.models.RepositoryModel
import com.example.githubrepoapp.presentation.appcomponents.ErrorItem
import com.example.githubrepoapp.presentation.appcomponents.LoadingItem

@Composable
fun RepositoryList(navController: NavController, data : LazyPagingItems<RepositoryModel>) {
    LazyColumn {
        items(data.itemCount) { index ->
            RepositoryItem(navController = navController, data[index])
        }
        data.apply {

            when (loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> item {
                    LoadingItem(loading = true)
                }

                is LoadState.Error -> item {
                    ErrorItem(
                        message = stringResource(R.string.something_wrong_happens),
                    )

                }
            }

            when (loadState.refresh) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> item {
                    Surface {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            LoadingItem(loading = true)
                        }
                    }


                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            message = stringResource(R.string.something_wrong_happens),
                        )
                    }
                }
            }

        }
    }
}


