package org.gspi.protrack.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.gspi.protrack.common.local.UserPreferences
import org.gspi.protrack.createDataStoreIos
import org.koin.dsl.module

fun iosHttpClient(myDataManager: UserPreferences): HttpClient {
    return HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url("https://gspi-protrack.my.id/")
            if (!headers.contains("No-Authentication") && myDataManager.getToken()?.isNotEmpty() == true) {
                header("Authorization", "Bearer ${myDataManager.getToken()}")
            }
        }
    }
}



val iosModule = module {
    single { iosHttpClient(get()) }
    single<DataStore<Preferences>> {
        createDataStoreIos()
    }
}