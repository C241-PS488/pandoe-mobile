package com.pandoe.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenerateIdeaResponse(

	@field:SerializedName("data")
	val data: List<String?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: String? = null
)
