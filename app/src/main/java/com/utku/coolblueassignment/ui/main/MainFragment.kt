package com.utku.coolblueassignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.utku.coolblueassignment.databinding.FragmentMainBinding
import com.utku.coolblueassignment.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>({ FragmentMainBinding.inflate(it) }) {

    override val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding.apply {

        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}