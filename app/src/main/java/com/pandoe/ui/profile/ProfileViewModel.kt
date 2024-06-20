package com.pandoe.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandoe.data.model.User
import com.pandoe.data.repository.AuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: AuthRepository) : ViewModel() {

    fun getUser(): LiveData<User> {
        return repository.getUserData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }
}