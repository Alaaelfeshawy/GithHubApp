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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubrepoapp.R

@Composable
fun ErrorFullScreenUI(modifier: Modifier = Modifier, message: String) {
    Column(
        modifier = modifier.padding(10.dp).
        background(Color.White).fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.exclamation),
            contentDescription = "exclamation")
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            maxLines = 2
        )
    }
}
@Preview
@Composable
fun ErrorFullScreen() {
    Column(
        modifier = Modifier.padding(10.dp).
        background(Color.White).fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.exclamation),
            contentDescription = "exclamation")
        Text(
            text = "Some thing went wrong",
            color = MaterialTheme.colorScheme.error,
            maxLines = 2
        )
    }
}
