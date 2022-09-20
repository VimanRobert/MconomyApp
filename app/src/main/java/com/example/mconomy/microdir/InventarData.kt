package com.example.mconomy.microdir



class InventarData {
    var produs: String = ""
    var pret: Double = 0.0
    var cantitate: Double = 0.0
    var rez: Double = 0.0
    var rezTotal: Double = 0.0

    constructor(produs: String, pret: Double, cantitate: Double, rez: Double, rezTotal: Double){
        this.produs = produs
        this.pret = pret
        this.cantitate = cantitate
        this.rez = rez
        this.rezTotal = rezTotal
    }
}