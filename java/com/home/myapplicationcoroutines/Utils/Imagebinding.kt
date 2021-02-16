package com.RadmanHayati.applicationcoroutines.Utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object Imagebinding {
    @BindingAdapter("image")
    @JvmStatic
    fun Getimage(view : ImageView,url:String){
        Glide.with(view).load(url).into(view)
    }
}