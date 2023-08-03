package com.example.currentconvertermvvm.utils

import com.example.currentconvertermvvm.data.models.ExchangeResponse

sealed interface ConvertEvent{
    data class Success(val result:ExchangeResponse): ConvertEvent
    data class Error(val errorMessage:String?): ConvertEvent
    object Loading: ConvertEvent
    object Empty: ConvertEvent
}