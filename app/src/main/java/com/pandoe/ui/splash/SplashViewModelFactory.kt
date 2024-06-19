package com.pandoe.ui.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.repository.AuthRepository
import com.pandoe.di.AuthInjection

class SplashViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
    }

    companion object {
        @Volatile
        private var instance: SplashViewModelFactory? = null
        fun getInstance(context: Context): SplashViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: SplashViewModelFactory(AuthInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}