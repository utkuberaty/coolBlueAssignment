package com.utku.coolblueassignment.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.utku.coolblueassignment.data.entitity.Product
import com.utku.coolblueassignment.data.entitity.ProductResponse
import retrofit2.HttpException

class ProductDataSource(
    private val request: suspend (Int) -> ProductResponse?
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val pageIndex = params.key ?: 1
        return try {
            val response = request.invoke(pageIndex)
            val products = response?.products
            if (products != null) {
                val nextKey = if (response.pageCount != response.currentPage)
                    response.currentPage + 1 else null

                LoadResult.Page(
                    data = products,
                    prevKey = if (pageIndex == 1) null else pageIndex,
                    nextKey = nextKey
                )
            } else LoadResult.Error(Throwable("Empty Data"))
        } catch (e: Throwable) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}