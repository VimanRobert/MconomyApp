package com.example.mconomy.microdir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionViewModel : ViewModel(){

    private val repos: SessionRepos
    private val listInv1 = MutableLiveData<List<SessionData>>()
    val listInv2 : LiveData<List<SessionData>> = listInv1

    init {
        repos = SessionRepos().getInst()
        repos.loadInventarData(listInv1)
    }
}