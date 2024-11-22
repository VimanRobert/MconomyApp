package com.example.mconomy.stock

import retrofit2.Call
import retrofit2.http.GET

interface StockAPI {
    @GET("open-close")
    fun getValues(): Call<List<StockData>>
}