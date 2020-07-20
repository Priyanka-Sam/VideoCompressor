package com.example.videocompressor.ui.screen3

import com.example.videocompressor.ui.base.BaseViewModel
import android.view.View
import androidx.databinding.ObservableField

class Screen3ViewModel : BaseViewModel<Screen3Events>() {

    val url = ObservableField<String>()

    val onPausePlay = View.OnClickListener {
        if (event.isPlaying) {
            event.onVideoPause()
        } else {
            event.onVideoPlay()
        }
    }
}