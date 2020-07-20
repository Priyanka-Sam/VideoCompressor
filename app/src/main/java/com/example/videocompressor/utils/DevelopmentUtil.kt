package com.example.videocompressor.utils

//import com.example.videocompressor.BuildConfig
import android.util.Log
import androidx.databinding.library.BuildConfig

object DevelopmentUtil {

}

object MLog {
    val debug: Boolean by lazy { BuildConfig.DEBUG }

    inline fun <reified T : Any> d(clazz: T, message: String) {
        if (debug) {
            Log.d(clazz::class.java.enclosingMethod?.name, message)
        }
    }

    inline fun <reified T : Any> d(clazz: T, exception: Exception) {
        if (debug) {
            Log.d(clazz::class.java.enclosingMethod?.name, "Exception!!")
            exception.printStackTrace()
        }
    }
}