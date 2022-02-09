package com.utku.coolblueassignment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mancj.materialsearchbar.MaterialSearchBar
import com.utku.coolblueassignment.databinding.FragmentMainBinding
import com.utku.coolblueassignment.ui.base.BaseFragment
import com.utku.coolblueassignment.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>({ FragmentMainBinding.inflate(it) }) {

    private val productAdapter by lazy { ProductAdapter() }

    override val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onError()
        onSearch()
        setSearchBar()
        setRecyclerView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun onSearch() {
        viewModel.searchQuery.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                productAdapter.apply {
                    refresh()
                    lifecycleScope.launch {
                        loadStateFlow.collect {
                            viewBinding.productsRecyclerView.smoothScrollToPosition(0)
                        }
                    }
                }
            }
        }
    }

    private fun onError() {
        lifecycleScope.launch {
            viewModel.onError.collect {
                Log.i(TAG, "${it?.exception}")
            }
        }
    }

    private fun setSearchBar() {
        viewBinding.productsSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) viewModel.searchQuery.value = (query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun setRecyclerView() {
        viewBinding.productsRecyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
        lifecycleScope.launch {
            submitData()
        }
    }

    private suspend fun submitData() {
        viewModel.searchProduct().collectLatest {
            productAdapter.submitData(it)
        }
    }
}