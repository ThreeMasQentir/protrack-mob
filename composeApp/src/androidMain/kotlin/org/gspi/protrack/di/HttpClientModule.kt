package org.gspi.protrack.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

fun androidHttpClient(context: Context): HttpClient {
    println("androidHttpClient: called")
    return HttpClient(OkHttp) {
        engine {
            preconfigured = OkHttpClient.Builder()
                .connectionSpecs(listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(
                    ChuckerInterceptor.Builder(context)
                        .collector(ChuckerCollector(context))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                ).build()
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
//        install(DefaultRequest) {
//            if (!headers.contains("No-Authentication") && myDataManager.getToken()
//                    ?.isNotEmpty() == true
//            ) {
//                header("Authorization", "Bearer ${myDataManager.getToken()}")
//            }
//        }
    }
}

val androidModule = module {
    single { androidHttpClient(get()) }
}