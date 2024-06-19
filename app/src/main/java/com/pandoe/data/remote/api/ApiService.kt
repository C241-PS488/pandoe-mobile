package com.pandoe.data.remote.api

import com.pandoe.data.remote.request.LoginRequest
import com.pandoe.data.remote.request.RegisterRequest
import com.pandoe.data.remote.response.LoginResponse
import com.pandoe.data.remote.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("/api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse
}