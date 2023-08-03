package com.example.currentconvertermvvm.main

import com.example.currentconvertermvvm.data.ConvertorApi
import com.example.currentconvertermvvm.data.models.ExchangeResponse
import com.example.currentconvertermvvm.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: ConvertorApi
):MainRepository {
    override suspend fun convertRate(
        from: String,
        to: String,
        amount: String,
    ): Resource<ExchangeResponse> {
        return try {
            val response =api.convertRate(from, to, amount)
            if (response.isSuccessful&&response.body() != null){
                Resource.Success(response.body())
            }else {
                Resource.Error(response.message())
            }
        }catch (e:Exception){
           Resource.Error(e.message)
        }
    }
}