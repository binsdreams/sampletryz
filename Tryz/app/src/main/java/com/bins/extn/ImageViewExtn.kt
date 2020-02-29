package com.bins.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imageUrl: String) {

    val requestOptions = RequestOptions()
    requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
    requestOptions.transform(CircleCrop())

    Glide.with(this)
        .load(imageUrl)
        .apply(requestOptions)
        .into(this)
}
