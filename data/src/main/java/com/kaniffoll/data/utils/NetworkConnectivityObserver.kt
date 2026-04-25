package com.kaniffoll.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import jakarta.inject.Inject
import kotlin.jvm.java

class NetworkConnectivityObserver @Inject constructor (
    context: Context
) {
    private val connectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}