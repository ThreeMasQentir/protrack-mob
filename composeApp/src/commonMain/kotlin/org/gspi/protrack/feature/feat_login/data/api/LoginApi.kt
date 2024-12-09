package org.gspi.protrack.feature.feat_login.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class LoginApi(private val client: HttpClient){

    suspend fun login(){
        println("LoginApi: called")
        client.get("https://gspi-protrack.my.id/login")
    }

}