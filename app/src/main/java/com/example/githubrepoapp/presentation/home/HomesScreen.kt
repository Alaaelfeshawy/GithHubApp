package com.example.githubrepoapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubrepoapp.NavigationItem
import com.example.githubrepoapp.data.network.models.RepositoryModel

@Composable
fun HomesScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
     val result  = viewModel.getRepos().collectAsLazyPagingItems()
    val state by viewModel.state.collectAsState()

    if (state.isLoading == true){
        LoadingItem()
    }
    PagingListScreen(navController = navController, data = result)

    if (state.errorMessage != null){
        Text(text = state.errorMessage ?: "Unknown Error")
    }


}

@Composable
fun PagingListScreen(navController: NavController,data : LazyPagingItems<RepositoryModel>) {

    LazyColumn {
        items(data.itemCount) { index ->
            RepositoryItem(navController = navController, data[index])
        }
        data.apply {

            when (loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> item{
                    LoadingItem()
                }

                is LoadState.Error -> item {
                    ErrorMessageUI(
                        modifier = Modifier,
                        message =  "Something wrong happens please retry again",
                        onClickRetry = { retry() }
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
                          LoadingItem()
                      }
                  }


                }

                is LoadState.Error -> {
                    item {
                        ErrorMessageUI(
                            modifier = Modifier,
                            message =  "Something wrong happens please retry again",
                            onClickRetry = { retry() }
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ErrorMessageUI(modifier: Modifier, message: String, onClickRetry: () -> Unit) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "stringResource(id = R.string.strRetry)")
        }
    }
}

@Composable
fun RepositoryItem(navController: NavController, model: RepositoryModel?) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.White)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(12.dp)
                .clickable {
                    navController.navigate("${NavigationItem.Details.route}/${model?.owner?.login}/${model?.name}")
                },
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 8.dp)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Person, null)
                    Text(
                        text = "${model?.owner?.login}",
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "${model?.id}",
                        style = TextStyle(fontSize = 18.sp),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "${model?.name}",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true
                    )
                    Text(
                        text = "${model?.description}",
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(top = 8.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 8.dp, end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Star, null , tint = Color.Yellow)
                    Text(text = "COUNT", modifier = Modifier.padding(top = 8.dp))
                }
            }
        }
    }
}

@Composable
fun CreateRepositoriesList(navController: NavController, data: LazyPagingItems<RepositoryModel>) {
    val scrollState = rememberLazyListState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        LazyColumn(state = scrollState) {
            items(data.itemCount) { index ->
                RepositoryItem(navController, data[index])
            }
            data.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val error = data.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessageUI(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage ?: "",
                                onClickRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            LoadingItem(modifier = Modifier)
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val error = data.loadState.append as LoadState.Error
                        item {
                            ErrorMessageUI(
                                modifier = Modifier,
                                message = error.error.localizedMessage ?: "",
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}
