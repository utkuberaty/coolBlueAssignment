package com.utku.coolblueassignment.data.remote

class RemoteDataSource(private val coolBlueService: CoolBlueService) : Call() {

    suspend fun searchProduct(query: String, page: Int) = call {
        coolBlueService.searchProducts(query, page)
    }
}