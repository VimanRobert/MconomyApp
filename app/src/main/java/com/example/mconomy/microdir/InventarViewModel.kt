package com.example.mconomy.microdir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InventarViewModel : ViewModel(){

    private val repos: InventarRepos = InventarRepos().getInst()
    private val listInv1 = MutableLiveData<List<InventarData>>()
    val listInv2 : LiveData<List<InventarData>> = listInv1

    init {
        repos.loadInventarData(listInv1)
    }

}