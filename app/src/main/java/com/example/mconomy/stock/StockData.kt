package com.example.mconomy.stock

data class StockData(
    val symbol: String? = null,
    val open: Double? = 0.0,
    val high: Double? = 0.0,
    val low: Double? = 0.0,
    val volume: Long? = 0
)
