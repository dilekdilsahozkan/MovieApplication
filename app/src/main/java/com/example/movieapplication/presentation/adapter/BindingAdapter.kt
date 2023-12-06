package com.example.movieapplication.presentation.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("movie:src")
fun ImageView.loadImage(mediaUrl: String?) {
    Glide.with(context).clear(this)
    Glide.with(context).load(mediaUrl)
        .dontAnimate()
        .into(this)
}

@BindingAdapter("movie:dayMonthYearDateFormat")
fun TextView.dayMonthYearDateFormat(date: String?) {
    val a = date?.split("-")
    val newVal = a?.get(2)?.split("T")?.get(0)
    text = "${newVal}.${a?.get(1)}.${a?.get(0)}"
}
