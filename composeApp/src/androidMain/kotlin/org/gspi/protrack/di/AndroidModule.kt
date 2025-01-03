package org.gspi.protrack.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import app.cash.sqldelight.db.SqlDriver
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import org.gspi.protrack.AndroidConnectivityChecker
import org.gspi.protrack.common.local.AndroidDatabaseDriverFactory
import org.gspi.protrack.common.local.DatabaseDriverFactory
import org.gspi.protrack.common.local.UserPreferences
import org.gspi.protrack.common.network.ConnectivityChecker
import org.gspi.protrack.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

fun androidHttpClient(context: Context, myDataManager: UserPreferences): HttpClient {
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
        install(DefaultRequest) {
            if (!headers.contains("No-Authentication") && myDataManager.getToken()
                    ?.isNotEmpty() == true
            ) {
                header("Authorization", "Bearer ${myDataManager.getToken()}")
            }
        }
    }
}


val androidModule = module {
    single { androidHttpClient(get(), get()) }
    single<DataStore<Preferences>> {
        createDataStore(androidContext())
    }
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
    single<ConnectivityChecker> { AndroidConnectivityChecker(get()) }
}