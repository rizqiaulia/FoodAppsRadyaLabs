package com.rapps.foodappsradyalabs.utils

sealed class ViewModelState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ViewModelState<T>(data)
    class Error<T>(message: String,data:T? = null) :ViewModelState<T>(data,message)
    class Loading<T> : ViewModelState<T>()
}

