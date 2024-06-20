package com.pandoe.data.remote.request

import com.google.gson.annotations.SerializedName

data class GenerateIdeaRequest(
	@SerializedName("pekerjaan")
	val pekerjaan: String? = null,
	@SerializedName("lama_pengalaman")
	val lamaPengalaman: String? = null,
	@SerializedName("aset")
	val aset: String? = null,
	@SerializedName("hobi")
	val hobi: String? = null
)
