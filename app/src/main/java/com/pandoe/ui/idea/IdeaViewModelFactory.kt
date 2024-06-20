package com.pandoe.ui.idea

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pandoe.data.repository.IdeaRepository
import com.pandoe.di.IdeaInjection

class IdeaViewModelFactory(private val repository: IdeaRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IdeaViewModel::class.java)) {
            return IdeaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.simpleName)
    }

    companion object {
        @Volatile
        private var instance: IdeaViewModelFactory? = null
        fun getInstance(context: Context): IdeaViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: IdeaViewModelFactory(IdeaInjection.provideRepository(context))
            }.also { instance = it }
        }
    }
}