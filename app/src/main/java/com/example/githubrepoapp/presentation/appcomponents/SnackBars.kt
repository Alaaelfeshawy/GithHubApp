package com.example.githubrepoapp.presentation.appcomponents

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GenericSnackbar(
    message: String,
    actionText: String? = null,
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = {
            DisposableEffect(snackbarHostState) {
                if (message.isNotEmpty()) {
                    // Show the snackbar
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = actionText,
                        )
                    }
                }

                onDispose {
                    // Clean up any resources if needed
                }
            }
        },
    )
}