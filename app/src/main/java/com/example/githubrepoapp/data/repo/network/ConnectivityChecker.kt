package com.example.githubrepoapp.data.repo.network

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext

interface ConnectivityChecker {
    fun isConnected(): Boolean
}

class NetworkConnectivityChecker(
    @ApplicationContext private val context: Context
) : ConnectivityChecker {
    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
