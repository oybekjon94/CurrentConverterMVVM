package com.example.currentconvertermvvm.utils

sealed class Resource<T>(data:T?, message:String? = null){
    data class Success<T>(val data: T?):Resource<T>(data,null)
    data class Error<T>(val message: String?):Resource<T>(null,message)
}