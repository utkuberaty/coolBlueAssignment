package com.utku.coolblueassignment.data.repository

import com.utku.coolblueassignment.data.entitity.Request
import com.utku.coolblueassignment.data.remote.RemoteDataSource
import com.utku.coolblueassignment.data.remote.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * [CoolBlueRepositoryIml] injects via hilt
 * Uses remote and local data sources
 * */

class CoolBlueRepositoryIml @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CoolBlueRepository, BaseRepository() {

    override fun searchProducts(query: String, page: Int): Flow<Result<Request>> =
        performGetOperation({ remoteDataSource.searchProduct(query, page) })
}