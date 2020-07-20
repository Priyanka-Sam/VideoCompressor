package com.example.videocompressor.ui.screen3

import com.example.videocompressor.ui.base.BaseEvents

interface Screen3Events : BaseEvents {
    fun onVideoPause()
    fun onVideoPlay()
    val isPlaying: Boolean
}