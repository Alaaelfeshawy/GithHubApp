package com.example.githubrepoapp.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.githubrepoapp.NavigationItem
import com.example.githubrepoapp.data.network.models.RepositoryModel

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
