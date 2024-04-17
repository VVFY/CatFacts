package com.example.data.remote.utils

import com.example.domain.response.ErrorCode
import retrofit2.Response
import java.io.IOException

inline fun <reified D: Any> Response<D>.response(): com.example.domain.response.Response<D> {
    val responseBody = body()
    return if (isSuccessful && responseBody != null) {
        com.example.domain.response.Response.Success(responseBody)
    } else {
        com.example.domain.response.Response.Error(ErrorCode.API_ERROR, errorBody()?.string())
    }
}
