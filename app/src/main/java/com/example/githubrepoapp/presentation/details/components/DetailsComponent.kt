package com.example.githubrepoapp.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.githubrepoapp.R
import com.example.githubrepoapp.data.network.models.RepositoryDetailsModel
import com.example.githubrepoapp.route.NavigationItem


@Composable
fun DetailsComponent(navController: NavController, model:RepositoryDetailsModel) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconWithText(icon = Icons.Default.Star , "stars",model.stargazers_count.toString() ?: "")
                Spacer(modifier = Modifier.width(15.dp))
                IconWithText(icon = Icons.Default.Star , "forks",model.forks_count.toString() ?: "")
                Spacer(modifier = Modifier.width(15.dp))
                IconWithText(icon = Icons.Default.Star , "watchers",model.watchers_count.toString() ?: "")


            }
            Row(modifier = Modifier.fillMaxWidth()) {
                NetworkImageExample(
                    modifier = Modifier.size(150.dp),
                    imageUrl = model.avatar_url ?: ""
                )
                Text(text = model.description ?: "" , modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically))
            }
            Text(text = model.owner?.login ?: "" , modifier = Modifier.padding(horizontal = 16.dp , vertical = 12.dp),
                style = TextStyle(fontSize = 16.sp , fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Text(text = model.full_name ?: "" , modifier = Modifier.padding(horizontal = 16.dp , vertical = 6.dp),
                style = TextStyle(fontSize = 16.sp , fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Button(onClick = {
            navController.navigate("${NavigationItem.Issue.route}/${model.owner?.login}/${model.name}")
                             } , modifier =Modifier.width(150.dp).height(40.dp).align(Alignment.CenterHorizontally)
            ) {
            Text(text = "Go to Issues")
            }
        }

    }
}

@Preview
@Composable
fun DetailsComponent1() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.White
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconWithText(Icons.Default.Star , "text" , "count")
                Spacer(modifier = Modifier.width(15.dp))
                IconWithText(Icons.Default.Star , "text", "count")
                Spacer(modifier = Modifier.width(15.dp))
                IconWithText(Icons.Default.Star , "text", "count")

            }
           Row(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
               Image(
                   modifier = Modifier.size(150.dp),
                   painter = painterResource(id = R.drawable.no_data_found),
                   contentDescription = "user image")
               Text(text = "here will set description isa here will set description isahere will set description isahere will set description isahere will set description isa" ,
                   modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically))
           }
           Text(text = "name" , modifier = Modifier.padding(horizontal = 16.dp , vertical = 12.dp),
                style = TextStyle(fontSize = 16.sp , fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
                )
            Button(onClick = {} , modifier =
            Modifier.width(150.dp).height(40.dp).align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Go to Issues")
            }
        }

    }
}


@Composable
fun IconWithText(icon : ImageVector , text : String , count : String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp) // Adjust padding as needed
        )
        Column {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            )
            Text(
                text = count,
                textAlign = TextAlign.Center,
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

@Composable
fun NetworkImageExample(imageUrl: String , modifier: Modifier= Modifier) {

    val painter = rememberImagePainter(imageUrl)

    Image(
        painter = painter,
        contentDescription = null, // Provide content description if needed
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun NetworkImagePreview() {
    NetworkImageExample("https://avatars.githubusercontent.com/u/1?v=4")
}