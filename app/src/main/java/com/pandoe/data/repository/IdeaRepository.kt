package com.pandoe.data.repository

import com.pandoe.data.remote.api.ApiService

class IdeaRepository(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: IdeaRepository? = null
        fun getInstance(
            apiService: ApiService
        ): IdeaRepository =
            instance ?: synchronized(this) {
                instance ?: IdeaRepository(apiService)
            }.also { instance = it }
    }
}