package com.example.usersclient.data.network.api

import com.example.usersclient.data.network.models.UsersResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2")
    suspend fun getUsers(): UsersResponse
}