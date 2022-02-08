package com.utku.coolblueassignment.data.entitity

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val products: List<Product> = listOf(),
    val currentPage: Int = 0,
    val pageSize: Int = 24,
    val totalResults: Int = 70,
    val pageCount: Int = 3
)
