package com.pandoe.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.pandoe.data.preference.UserPreference
import com.pandoe.data.repository.AuthRepository
import com.pandoe.data.remote.api.ApiConfig

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("auth")

object AuthInjection {
    fun provideRepository(context: Context): AuthRepository {
        val preferences = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiClient()
        return AuthRepository.getInstance(preferences, apiService)
    }
}