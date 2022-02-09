package com.utku.coolblueassignment.data.entitity

import kotlinx.serialization.Serializable

@Serializable
data class ReviewSummary(
    val reviewAverage: Float = 0.0F,
    val reviewCount: Int = 0
)
