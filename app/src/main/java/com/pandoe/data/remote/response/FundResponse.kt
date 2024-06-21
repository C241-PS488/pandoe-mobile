package com.pandoe.data.remote.response

import com.google.gson.annotations.SerializedName

data class FundResponse(

	@field:SerializedName("FundResponse")
	val fundResponse: List<FundResponseItem?>? = null
)

data class FundResponseItem(

	@field:SerializedName("pitchDeck")
	val pitchDeck: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null,

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("amountRaised")
	val amountRaised: Int? = null,

	@field:SerializedName("executiveSummary")
	val executiveSummary: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
