package com.pandoe.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pandoe.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }

        private val TOKEN = stringPreferencesKey("token")
        private val EMAIL = stringPreferencesKey("email")
        private val NAME = stringPreferencesKey("name")
        private val STATE = booleanPreferencesKey("STATE")
    }

    fun getUserData(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[NAME] ?: "",
                preferences[TOKEN] ?: "",
                preferences[STATE] ?: false
            )
        }
    }

    suspend fun saveUserData(user: User) {
        dataStore.edit { preferences ->
            preferences[NAME] = user.name
            preferences[TOKEN] = user.token
            preferences[STATE] = user.isLogin
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    fun getUser(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun deleteToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN)
        }
    }

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[STATE] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}