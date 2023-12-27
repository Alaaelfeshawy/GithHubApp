package com.example.githubrepoapp.presentation.appcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubrepoapp.R

@Composable
fun EmptyScreen(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.no_data_found),
            contentDescription = "exclamation")
        Text(
            text = stringResource(R.string.no_data_found_please_try_later),
            color = Color.LightGray,
            style = TextStyle(fontSize = 16.sp,fontWeight = FontWeight.Bold),
            maxLines = 2
        )
    }
}
@Preview
@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.no_data_found),
            contentDescription = "exclamation")
        Text(
            text = "No Data found please try later",
            color = Color.LightGray,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 2
        )
    }
}
