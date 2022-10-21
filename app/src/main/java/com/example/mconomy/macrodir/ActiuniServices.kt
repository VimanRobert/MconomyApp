package com.example.mconomy.macrodir

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class ActiuniServices {
    private val TESLA_URL = "https://www.google.com/finance/quote/TSLA:NASDAQ"
    private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(TESLA_URL).build()


    interface ActiuniServices {
        @GET("TSLA:NASDAQ")
        fun getStockData() : String
    }
/*
    object ActiuniApi{
        val retrofitService :  ActiuniServices by lazy {

        }
    }

 */
}