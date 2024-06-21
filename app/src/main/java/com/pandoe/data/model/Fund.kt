package com.pandoe.data.model

data class Fund(
    val id: String,
    val userId: String,
    val title: String,
    val executiveSummary: String,
    val thumbnail: String,
    val pitchDeck: String,
    val amountRaised: Int,
    val endDate: String,
)