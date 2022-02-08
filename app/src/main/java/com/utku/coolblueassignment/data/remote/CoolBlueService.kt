package com.utku.coolblueassignment.data.remote

import com.utku.coolblueassignment.data.entitity.Request
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoolBlueService {

    @GET("mobile-assignment/search")
    suspend fun searchProducts(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<Request>
}