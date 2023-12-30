package com.example.githubrepoapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun extractDate(dateString: String?): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    val dateTime = LocalDateTime.parse(dateString, inputFormatter)

    val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy")

    return dateTime.format(outputFormatter)
}
