package com.pandoe.data.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val token: String,
    val isLogin: Boolean
)