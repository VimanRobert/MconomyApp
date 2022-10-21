package com.example.mconomy.microdir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionViewModel : ViewModel(){

    private val repos: SessionRepos = SessionRepos().getInst()
    private val listInv2 = MutableLiveData<List<SessionData>>()
    val listInv1 : LiveData<List<SessionData>> = listInv2

    init {
        repos.loadInventarData(listInv2)
        //listInv1.value = repos.
    }
}