package com.utku.coolblueassignment.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.utku.coolblueassignment.data.entitity.ProductResponse
import com.utku.coolblueassignment.data.pager.ProductDataSource
import com.utku.coolblueassignment.data.remote.Result
import com.utku.coolblueassignment.data.repository.CoolBlueRepository
import com.utku.coolblueassignment.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val PAGE_SIZE = 3

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coolBlueRepository: CoolBlueRepository
) : BaseViewModel() {

    val searchQuery = MutableLiveData("")
    val onError = MutableStateFlow<Result.Error?>(null)

    fun searchProduct() = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = { ProductDataSource { fetchProducts(searchQuery.value ?: "", it) } }
    ).flow

    private suspend fun fetchProducts(query: String, page: Int): ProductResponse? {
        return withContext(Dispatchers.IO) {
            var response: ProductResponse? = null
            coolBlueRepository.searchProducts(query, page).collect {
                response = when (it) {
                    is Result.Success -> it.data
                    is Result.Error -> {
                        onError.value = it
                        null
                    }
                }
            }
            return@withContext response
        }
    }

}