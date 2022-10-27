package com.example.mconomy


import com.squareup.moshi.Json


data class StockData (
    @Json(name = "symbol") val symbol: String? = null,
    @Json(name = "open") val open: Double? = 0.0,
    @Json(name = "high") val high: Double? =0.0,
    @Json(name = "low") val low: Double? = 0.0,
    @Json(name = "volume") val volume: Long? = 0
)