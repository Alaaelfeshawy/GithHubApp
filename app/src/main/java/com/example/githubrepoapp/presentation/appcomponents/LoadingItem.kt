package com.example.githubrepoapp.presentation.appcomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingItem(modifier: Modifier = Modifier , loading : Boolean) {
    if (loading){
        CircularProgressIndicator(
            modifier = modifier
                .fillMaxSize()
                .padding(10.dp)
                .wrapContentSize(Alignment.Center)
        )
    }else{
        Box{}
    }

}
