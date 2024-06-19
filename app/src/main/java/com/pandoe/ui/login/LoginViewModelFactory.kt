package com.pandoe.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.repository.AuthRepository
import com.pandoe.di.AuthInjection


class LoginViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
    }

    companion object {
        @Volatile
        private var instance: LoginViewModelFactory? = null
        fun getInstance(context: Context): LoginViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: LoginViewModelFactory(AuthInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}