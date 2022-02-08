package com.utku.coolblueassignment.data.entitity

import kotlinx.serialization.Serializable

@Serializable
data class ReviewSummary(
    val reviewAverage: String = "",
    val reviewCount: Int = 0
)
