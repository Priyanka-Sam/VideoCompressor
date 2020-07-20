package com.example.videocompressor.ui.screen3

import com.example.videocompressor.R
import com.example.videocompressor.databinding.ActivityScreen3Binding
import com.example.videocompressor.ui.base.BaseActivity
import com.example.videocompressor.utils.Constants
import android.os.Bundle
import org.koin.core.inject


class Screen3Activity : BaseActivity(), Screen3Events {
    private val model: Screen3ViewModel by inject()
    private val binding: ActivityScreen3Binding by lazyBinding()

    override val layoutResource = R.layout.activity_screen_3
    override fun getViewModel() = model

    override val isPlaying: Boolean get() = binding.videoView.isPlaying

    private var currentPosition: Int? = null
    private var wasPlaying: Boolean = false

    private val url: String? by lazy { intent.extras?.getString(Constants.IntentConstants.VIDEO_URI) }

    override fun setBindings() {
        binding.model = model
        url?.let {
            model.url.set(it)
        }
    }

    override fun onPause() {
        binding.videoView.apply {
            wasPlaying = isPlaying
            this@Screen3Activity.currentPosition = currentPosition
            pause()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.videoView.apply {
            this@Screen3Activity.currentPosition?.let {
                seekTo(it)
            }
            if (wasPlaying) {
                start()
            }
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {

    }

    override fun onVideoPause() {
        binding.videoView.pause()
        binding.imageView.setImageDrawable(getDrawable(R.drawable.ic_baseline_play_arrow_24))
    }

    override fun onVideoPlay() {
        binding.videoView.start()
        binding.imageView.setImageDrawable(getDrawable(R.drawable.ic_baseline_pause_24))
    }

    override fun setEventHandler() {
        model.setEventHandler(this)
    }
}