package com.pandoe.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("data")
	val data: RegisterData? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class RegisterData(
	@field:SerializedName("isVerified")
	val isVerified: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
