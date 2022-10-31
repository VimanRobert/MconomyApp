package com.example.mconomy


//import com.squareup.moshi.Json


data class StockData (
    val symbol: String? = null,
    val open: Double? = 0.0,
    val high: Double? =0.0,
    val low: Double? = 0.0,
    val volume: Long? = 0
)