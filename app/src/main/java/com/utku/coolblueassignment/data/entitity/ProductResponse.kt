package com.utku.coolblueassignment.data.entitity

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val products: List<Product> = listOf(),
    val currentPage: Int = 0,
    val pageSize: Int = 0,
    val totalResults: Int = 0,
    val pageCount: Int = 0
)
