package com.pandoe.ui.fund

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.repository.AuthRepository
import com.pandoe.di.AuthInjection

class FundViewModelFactory(private val repository: AuthRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FundViewModel::class.java)) {
            return FundViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
    }

    companion object {
        @Volatile
        private var instance: FundViewModelFactory? = null
        fun getInstance(context: Context): FundViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: FundViewModelFactory(AuthInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}