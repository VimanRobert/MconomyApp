package com.example.mconomy.microdir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InventarViewModel : ViewModel(){

    private val repos: InventarRepos
    private val listInv1 = MutableLiveData<List<InventarData>>()
    val listInv2 : LiveData<List<InventarData>> = listInv1

    init {
        repos = InventarRepos().getInst()
        repos.loadInventarData(listInv1)
    }

}