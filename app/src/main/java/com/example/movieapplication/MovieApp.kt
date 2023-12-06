package com.example.movieapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp: Application() {

    companion object{
        var instance: MovieApp? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}