package com.example.data.remote.utils

import com.squareup.moshi.Moshi

internal object MoshiInstance {
    val default: Moshi = Moshi.Builder().build()
}