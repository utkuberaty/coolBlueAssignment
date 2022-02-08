package com.utku.coolblueassignment.data.repository

import com.utku.coolblueassignment.data.remote.Result
import kotlinx.coroutines.flow.flow

/**
 * Base repository for all repositories
 * [performGetOperation] performs local and remote operations
 * if local data is empty uses network call to refresh local data
 * */

open class BaseRepository {

    fun <T> performGetOperation(
        networkCall: (suspend () -> Result<T>)? = null,
        databaseQuery: (suspend () -> T?)? = null,
        saveCallResult: (suspend (T) -> Unit)? = null,
        useLocal: Boolean = true
    ) = flow<Result<T>> {

        val source = if (databaseQuery != null) Result.Success(databaseQuery.invoke())
        else Result.Success(null)

        val isDataNull = when (source.data) {
            is List<*> -> source.data.isNullOrEmpty()
            else -> source.data == null
        }

        if (((databaseQuery == null || isDataNull || !useLocal) && networkCall != null)) {
            when (val responseStatus = networkCall.invoke()) {
                is Result.Success -> {
                    saveCallResult?.invoke(responseStatus.data!!)
                    emit(Result.Success(responseStatus.data!!))
                }
                is Result.Error -> {
                    emit(Result.Error(responseStatus.code, responseStatus.exception))
                }
            }
        } else emit(source)
    }
}