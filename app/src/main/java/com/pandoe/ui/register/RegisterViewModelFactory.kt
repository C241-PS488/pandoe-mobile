package com.pandoe.ui.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.repository.AuthRepository
import com.pandoe.di.AuthInjection

class RegisterViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
    }

    companion object {
        @Volatile
        private var instance: RegisterViewModelFactory? = null
        fun getInstance(context: Context): RegisterViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: RegisterViewModelFactory(AuthInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}