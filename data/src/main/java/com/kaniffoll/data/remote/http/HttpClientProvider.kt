package com.kaniffoll.data.remote.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import java.security.SecureRandom
import javax.net.ssl.SSLContext

class HttpClientProvider {
    operator fun invoke() =
        // Причина по которoй используeтся OkHttp а не Android описана ниже
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json()
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.HEADERS
            }
            // api сломано, по этой причине приходится отключать проверку ssl сертификатов
            engine {
                config {
                    val trustAllCerts = AllCertsTrustManager()
                    val sslContext = SSLContext.getInstance("SSL")
                    sslContext.init(null, arrayOf(trustAllCerts), SecureRandom())
                    sslSocketFactory(sslContext.socketFactory, trustAllCerts)
                }
            }
        }
}