package com.ark.mvvmretrofitdb.model

sealed class Response<T>(private val data: T? = null, private val message: String? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(val data: T? = null) : Response<T>(data = data)
    class Error<T>(val message: String? = null) : Response<T>(message = message)
}