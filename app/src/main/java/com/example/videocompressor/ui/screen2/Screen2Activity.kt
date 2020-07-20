package com.example.videocompressor.ui.screen2

import com.example.videocompressor.R
import com.example.videocompressor.databinding.ActivityScreen2Binding
import com.example.videocompressor.ui.base.BaseActivity
import com.example.videocompressor.utils.Constants
import com.example.videocompressor.utils.navigateToScreen3
import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.core.inject


class Screen2Activity : BaseActivity(), Screen2Events {
    private val model: Screen2ViewModel by inject()
    private val binding: ActivityScreen2Binding by lazyBinding()

    override val layoutResource = R.layout.activity_screen_2
    override fun getViewModel() = model

    var dialog: AlertDialog? = null
    private var currentPosition: Int? = null

    override val url: Uri? by lazy { intent.extras?.getParcelable<Uri>(Constants.IntentConstants.VIDEO_URI) }

    override fun setBindings() {
        binding.model = model
        url?.let {
            model.url.set(it.toString())
        }
    }

    override fun onPause() {
        binding.videoView.apply {
            this@Screen2Activity.currentPosition = currentPosition
            pause()
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.videoView.apply {
            this@Screen2Activity.currentPosition?.let {
                seekTo(it)
                start()
            }
        }
    }

    override fun onDestroy() {
        dialog?.takeIf { it.isShowing }?.dismiss()
        super.onDestroy()
    }

    override fun onCompressionStart() {
        runOnUiThread {
            dialog?.takeIf { it.isShowing }?.dismiss()
            dialog = MaterialAlertDialogBuilder(this)
                .setView(R.layout.dialog_progress)
                .setCancelable(false)
                .create().apply {
                    show()
                }
        }
    }

    override fun onCompressionEnd(outputFilePath: String?) {
        runOnUiThread {
            dialog?.takeIf { it.isShowing }?.dismiss()
            outputFilePath?.let {
                navigateToScreen3(it)
            }
        }
    }

    override fun onCompressionProgress(progress: Int) {
        runOnUiThread {
            dialog?.findViewById<ProgressBar>(R.id.progress)?.apply {
                setProgress(progress)
            }
        }
    }

    override fun initUi(savedInstanceState: Bundle?) {

    }

    override fun setEventHandler() {
        model.setEventHandler(this)
    }
}