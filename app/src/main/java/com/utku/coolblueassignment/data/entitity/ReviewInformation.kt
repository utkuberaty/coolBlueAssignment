package com.utku.coolblueassignment.data.entitity

import kotlinx.serialization.Serializable

@Serializable
data class ReviewInformation(
    val reviews: List<String> = listOf(),
    val reviewSummary: ReviewSummary = ReviewSummary()
)
