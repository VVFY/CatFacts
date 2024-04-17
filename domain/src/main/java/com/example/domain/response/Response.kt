package com.example.domain.response

sealed class Response<out D : Any> {

    data class Success<out T : Any>(val data: T) : Response<T>()

    data class Error(val errorCode: ErrorCode? = null, val message: String? = null) : Response<Nothing>()

    inline fun onSuccess(block: (D) -> Unit): Response<D> {
        if (this is Success) {
            block(data)
        }
        return this
    }

    inline fun onError(block: (Error?) -> Unit): Response<D> {
        if (this is Error) {
            block(this)
        }
        return this
    }
}