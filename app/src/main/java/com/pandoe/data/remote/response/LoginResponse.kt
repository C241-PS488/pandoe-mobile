package com.pandoe.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val data: LoginData? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("error")
    val error: Any? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class LoginData(
    @field:SerializedName("token")
    val token: String? = null
)