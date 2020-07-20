package com.example.videocompressor.ui.screen1

import com.example.videocompressor.ui.base.BaseViewModel
import android.view.View

class Screen1ViewModel : BaseViewModel<Screen1Events>() {
    val onSelectClick = View.OnClickListener {
        fetchVideoFromGallery()
    }

    private fun fetchVideoFromGallery() {
        launchIO { event.fetchVideoFromGallery() }
    }
}