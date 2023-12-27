package com.example.githubrepoapp.presentation.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorItem(modifier: Modifier = Modifier, message: String) {
    Text(
        modifier = modifier.padding(10.dp).
        background(Color.White).fillMaxWidth().wrapContentHeight(),
        text = message,
        color = MaterialTheme.colorScheme.error,
        maxLines = 2,
        style = TextStyle(
            fontSize = 16.sp
        ),
        textAlign = TextAlign.Center
    )
}
@Preview
@Composable
fun ErrorItem() {
    Text(
        modifier = Modifier.padding(10.dp).
        background(Color.White).fillMaxWidth().wrapContentHeight(),
        text = "Some thing went wrong",
        color = MaterialTheme.colorScheme.error,
        maxLines = 2,
        style = TextStyle(
            fontSize = 16.sp
        ),
        textAlign = TextAlign.Center
    )
}