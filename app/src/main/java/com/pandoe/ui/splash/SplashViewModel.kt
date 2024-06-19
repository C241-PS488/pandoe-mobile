package com.pandoe.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pandoe.data.model.User
import com.pandoe.data.repository.AuthRepository

class SplashViewModel(private val repository: AuthRepository) : ViewModel() {

    fun getUser(): LiveData<User> {
        return repository.getUserData()
    }

}