package com.pandoe.di

import android.content.Context
import com.pandoe.data.remote.api.ApiConfig
import com.pandoe.data.repository.IdeaRepository


object IdeaInjection {
    fun provideRepository(context: Context): IdeaRepository {
        val apiService = ApiConfig.getApiClient()
        return IdeaRepository.getInstance(apiService)
    }
}