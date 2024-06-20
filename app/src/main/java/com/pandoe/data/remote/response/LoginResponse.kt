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

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class User(

	@field:SerializedName("duration")
	val duration: Any? = null,

	@field:SerializedName("courses")
	val courses: List<Any?>? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("assets")
	val assets: Any? = null,

	@field:SerializedName("hobbies")
	val hobbies: List<Any?>? = null,

	@field:SerializedName("fundingFeeds")
	val fundingFeeds: List<Any?>? = null,

	@field:SerializedName("contact")
	val contact: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("experiences")
	val experiences: List<Any?>? = null,

	@field:SerializedName("businesses")
	val businesses: List<Any?>? = null,

	@field:SerializedName("email")
	val email: String? = null
)
