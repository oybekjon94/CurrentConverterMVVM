package com.example.currentconvertermvvm.main

import com.example.currentconvertermvvm.data.models.ExchangeResponse
import com.example.currentconvertermvvm.utils.Resource

interface MainRepository {
    suspend fun convertRate(
        from:String,
        to:String,
        amount:String
    ):Resource<ExchangeResponse>

}