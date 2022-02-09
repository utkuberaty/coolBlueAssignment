package com.utku.coolblueassignment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.utku.coolblueassignment.R
import com.utku.coolblueassignment.data.remote.Result
import com.utku.coolblueassignment.databinding.ActivityMainBinding
import com.utku.coolblueassignment.ui.base.BaseActivity
import com.utku.coolblueassignment.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {

    override val viewModel: MainViewModel by viewModels()
}