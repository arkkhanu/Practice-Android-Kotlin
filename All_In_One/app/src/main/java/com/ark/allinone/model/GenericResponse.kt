package com.ark.allinone.model

sealed class GenericResponse<T>(private val data: T? = null, private val message: String? = null) {

    class Loading<T> : GenericResponse<T>()
    class Success<T>(private val data: T? = null) : GenericResponse<T>(data = data)
    class Error<T>(private val message: String? = null) : GenericResponse<T>(message = message)

}