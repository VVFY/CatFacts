package com.example.coreui.utils

import android.content.Context
import com.example.coreui.BuildConfig
import com.example.coreui.R
import com.example.domain.response.ErrorCode
import com.example.domain.response.Response

fun Context.resolveErrorMessage(error: Response.Error?): String? {
    return when {
        error?.errorCode != null -> {
            return when (error.errorCode) {
                ErrorCode.NETWORK_ERROR -> {
                    getString(R.string.network_error)
                }
                ErrorCode.API_ERROR -> {
                    getString(R.string.api_error)
                }
                else -> {
                    getString(R.string.unknown_error)
                }
            }
        }
        error?.message != null && BuildConfig.DEBUG -> {
            error.message
        }
        else -> null
    }
}