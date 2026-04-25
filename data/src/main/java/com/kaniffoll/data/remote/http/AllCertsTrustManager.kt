package com.kaniffoll.data.remote.http

import android.annotation.SuppressLint
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

internal class AllCertsTrustManager: X509TrustManager {

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(p0: Array<out X509Certificate>, p1: String) {
        //no-op
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(p0: Array<out X509Certificate>, p1: String) {
        //no-op
    }

    override fun getAcceptedIssuers(): Array<out X509Certificate> = arrayOf()
}