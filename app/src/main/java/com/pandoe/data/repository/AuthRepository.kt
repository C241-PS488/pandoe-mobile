package com.pandoe.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.pandoe.data.model.User
import com.pandoe.data.preference.UserPreference
import com.pandoe.data.remote.api.ApiService
import com.pandoe.data.remote.request.LoginRequest
import com.pandoe.data.remote.request.RegisterRequest
import com.pandoe.data.remote.response.LoginResponse
import com.pandoe.data.remote.response.RegisterResponse
import com.pandoe.data.result.Result

class AuthRepository(private val pref: UserPreference, private val apiService: ApiService) {
    fun userLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(LoginRequest(email, password))
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("Login", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun userRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(RegisterRequest(name, email, password))
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d("Signup", e.message.toString())
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUserData(): LiveData<User> {
        return pref.getUserData().asLiveData()
    }

    suspend fun saveUserData(user: User) {
        pref.saveUserData(user)
    }

    suspend fun login() {
        pref.login()
    }

    suspend fun logout() {
        pref.logout()
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            preferences: UserPreference,
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(preferences, apiService)
            }.also { instance = it }
    }
}