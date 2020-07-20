package com.example.videocompressor.ui.screen2

import com.example.videocompressor.ui.base.BaseEvents
import android.net.Uri

interface Screen2Events : BaseEvents {
    val url: Uri?
    fun onCompressionStart()
    fun onCompressionEnd(outputFilePath: String?)
    fun onCompressionProgress(progress: Int)
}