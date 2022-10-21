package com.example.mconomy.microdir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InventarViewModel : ViewModel(){

    private val repos: InventarRepos
    private val listInv2 = MutableLiveData<List<InventarData>>()
    val listInv1 : LiveData<List<InventarData>> = listInv2

    init {
        repos = InventarRepos().getInst()
        repos.loadInventarData(listInv2)
    }

}