package com.pandoe.ui.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.repository.AuthRepository
import com.pandoe.di.AuthInjection

class ProfileViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
    }

    companion object {
        @Volatile
        private var instance: ProfileViewModelFactory? = null
        fun getInstance(context: Context): ProfileViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ProfileViewModelFactory(AuthInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}