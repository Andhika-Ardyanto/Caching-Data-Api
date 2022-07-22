package com.example.caching_data_api

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("showImage")
fun showImage(imgView: ImageView, url: String?) {
    Glide.with(imgView.context)
        .load(url)
        .into(imgView);
}

@BindingAdapter("LoadStatus")
fun loadStatus(textView: TextView, status: String?){
    if (status == "OK"){
        textView.visibility = View.GONE
    }
}