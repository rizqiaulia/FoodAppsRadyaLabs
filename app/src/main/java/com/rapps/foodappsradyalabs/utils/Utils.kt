package com.rapps.foodappsradyalabs.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.rapps.foodappsradyalabs.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_baseline_broken_image_24)

    GlideApp.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}