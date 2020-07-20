package com.example.videocompressor.di

import com.example.videocompressor.ui.screen1.Screen1ViewModel
import com.example.videocompressor.ui.screen2.Screen2ViewModel
import com.example.videocompressor.ui.screen3.Screen3ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { Screen1ViewModel() }
    viewModel { Screen2ViewModel() }
    viewModel { Screen3ViewModel() }
}