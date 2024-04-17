package com.example.domain.response

import java.io.IOException

enum class ErrorCode {
    API_ERROR,
    NETWORK_ERROR,
    DISK_FULL,
    UNKNOWN
}

fun Throwable.resolveError(): Response.Error {
    return if (this is IOException) {
        Response.Error(ErrorCode.NETWORK_ERROR, localizedMessage)
    } else {
        Response.Error(ErrorCode.UNKNOWN, localizedMessage)
    }
}