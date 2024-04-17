package com.example.catfacts

import android.app.Application
import com.example.data.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CatFactApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}