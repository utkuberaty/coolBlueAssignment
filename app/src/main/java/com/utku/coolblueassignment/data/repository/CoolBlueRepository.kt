package com.utku.coolblueassignment.data.repository

import com.utku.coolblueassignment.data.entitity.ProductResponse
import com.utku.coolblueassignment.data.remote.Result
import kotlinx.coroutines.flow.Flow

interface CoolBlueRepository {

    fun searchProducts(query: String, page: Int) : Flow<Result<ProductResponse>>
}