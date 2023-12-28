package com.example.githubrepoapp.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubrepoapp.R


@Composable
fun DetailsComponent() {
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
                IconWithText()
                Spacer(modifier = Modifier.width(15.dp))
                IconWithText()
                Spacer(modifier = Modifier.width(15.dp))
                IconWithText()

            }
           Row(modifier = Modifier.fillMaxWidth()) {
               Image(
                   modifier = Modifier.size(150.dp),
                   painter = painterResource(id = R.drawable.no_data_found),
                   contentDescription = "user image")
               Text(text = "here will set description isa here will set description isahere will set description isahere will set description isahere will set description isa" , modifier = Modifier.fillMaxWidth())
           }
           Text(text = "name" , modifier = Modifier.padding(horizontal = 16.dp , vertical = 12.dp),
                style = TextStyle(fontSize = 16.sp , fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
                )
        }

    }
}


@Composable
fun IconWithText() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp) // Adjust padding as needed
        )
        Text(
            text = "Text",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .height(56.dp)
                .padding(vertical = 16.dp)
        )
    }
}
