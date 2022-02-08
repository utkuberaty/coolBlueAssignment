package com.utku.coolblueassignment.ui.main

import com.utku.coolblueassignment.data.repository.CoolBlueRepository
import com.utku.coolblueassignment.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coolBlueRepository: CoolBlueRepository
) : BaseViewModel() {

    fun searchProduct(query: String, page: Int) =
        coolBlueRepository.searchProducts(query, page)

}