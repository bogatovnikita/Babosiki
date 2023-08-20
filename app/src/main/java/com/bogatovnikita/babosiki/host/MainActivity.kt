package com.bogatovnikita.babosiki.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bogatovnikita.babosiki.R
import com.bogatovnikita.babosiki.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel() {
        val provider = ViewModelProvider(this)
        mainViewModel = provider[MainViewModel::class.java]
    }
}