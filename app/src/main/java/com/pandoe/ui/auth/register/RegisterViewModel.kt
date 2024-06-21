package com.pandoe.ui.auth.register

import androidx.lifecycle.ViewModel
import com.pandoe.data.repository.AuthRepository

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {

    fun userRegister(name: String, email: String, password: String) =
        repository.userRegister(name, email, password)
}