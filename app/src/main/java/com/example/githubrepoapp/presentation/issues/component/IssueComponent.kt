package com.example.githubrepoapp.presentation.issues.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubrepoapp.R
import com.example.githubrepoapp.data.network.models.IssueModel
import com.example.githubrepoapp.presentation.extractDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateIssueList(list : List<IssueModel>) {
    LazyColumn{
        items(list){
            IssueItem(it)
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IssueItem(item : IssueModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .wrapContentHeight(),) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RowItem(
                title = stringResource(R.string.title),
                desc = item.title ?: "" ,
                modifier = Modifier.weight(1f)
            )

            RowItem(modifier = Modifier.weight(1f),
                title = stringResource(R.string.author) ,
                desc = item.author ?: "")

            RowItem(modifier = Modifier.weight(1f),
                title = stringResource(R.string.state),
                desc = item.state ?: "")

            RowItem(
                modifier = Modifier.weight(1f) ,
                title  = stringResource(R.string.created_at),
                desc = extractDate(item.createdAt) ?: "")

        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun Item(item:String) {
    Text(
        text = item,
        textAlign = TextAlign.Start,
        maxLines = 5,
        style = TextStyle(
            fontSize = 14.sp
        ),
        color = Color.Gray
    )
}

@Composable
fun RowItem(title : String , desc : String , modifier: Modifier) {
    Column(modifier = modifier.padding(5.dp)) {
        Header(text = title)
        Item(desc)
    }
}

@Composable
private fun Header(text:String) {
    Text(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        text = text, fontWeight = FontWeight.Bold,
        style = TextStyle(fontSize = 16.sp),
    )
}