package com.example.mconomy.microdir

data class InventarData(
    val produs: String? = "",
    val pret: Double? = 0.00,
    val cantitate: Double? = 0.00,
    var rez: Double? = 0.00,
    var rezTotal: Double? = 0.00
)
