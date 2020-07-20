package com.example.videocompressor.ui.screen2

import com.example.videocompressor.binding.SpinnerConfiguration
import com.example.videocompressor.ui.base.BaseViewModel
import com.example.videocompressor.utils.Constants
import com.example.videocompressor.utils.GeneralUtils
import com.example.videocompressor.utils.MLog
import com.example.videocompressor.utils.VideoCompressor
import android.view.View
import android.widget.AdapterView
import androidx.databinding.ObservableField

class Screen2ViewModel : BaseViewModel<Screen2Events>() {

    val url = ObservableField<String>()
    private var selectedCompression: Constants.COMPRESSION_FORMATS =
        Constants.COMPRESSION_FORMATS.QUALITY_240

    val onCompressClick = View.OnClickListener {
        launchIO {
            compressVideo()
        }
    }

    private val compressionAdapter: CompressionAdapter by lazy {
        CompressionAdapter(
            event.parentContext,
            this@Screen2ViewModel
        )
    }

    val spinnerConfiguration
        get() = SpinnerConfiguration(compressionAdapter) { spinner ->
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    compressionAdapter.getItem(position)?.let {
                        selectedCompression = it
                    }
                    spinner.setSelection(position)
                }

            }
        }

    private fun compressVideo() {
        url.get()?.let {
            event.onCompressionStart()
            val path = GeneralUtils.getRealPathFromURI(event.parentContext, event.url)
            VideoCompressor(event.parentContext).startCompressing(
                path,
                selectedCompression,
                path.substring(path.lastIndexOf("/") + 1),
                object : VideoCompressor.CompressionListener {
                    override fun compressionFinished(
                        status: Int,
                        isVideo: Boolean,
                        fileOutputPath: String?
                    ) {
                        event.onCompressionEnd(fileOutputPath)
                    }

                    override fun onFailure(message: String?) {
                        message?.let {
                            MLog.d(this, Exception(message))
                            event.onGeneralError(it)
                            event.onCompressionEnd(null)
                        }
                    }

                    override fun onProgress(progress: Int) {
                        event.onCompressionProgress(progress)
                    }

                })
        }
    }
}