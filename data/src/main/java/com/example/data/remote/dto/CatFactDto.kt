package com.example.data.remote.dto

import com.squareup.moshi.JsonClass

/**
 * Internal data model for cat fact
 */

@JsonClass(generateAdapter = true)
data class CatFactDto(
    val fact: String,
    val length: Int
)
