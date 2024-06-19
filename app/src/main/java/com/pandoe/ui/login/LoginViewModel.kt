package com.pandoe.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandoe.data.model.User
import com.pandoe.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    fun userLogin(email: String, password: String) = repository.userLogin(email, password)

    fun saveUser(user: User) {
        viewModelScope.launch {
            repository.saveUserData(user)
        }
    }

    fun login() {
        viewModelScope.launch {
            repository.login()
        }
    }
}