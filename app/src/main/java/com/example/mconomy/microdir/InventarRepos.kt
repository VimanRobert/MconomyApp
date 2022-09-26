package com.example.mconomy.microdir

import android.renderscript.Sampler.Value
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class InventarRepos {
    private val invRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Inventar")

    @Volatile private var INSTANCE: InventarRepos?=null

    fun getInst() : InventarRepos{
        return INSTANCE?: synchronized(this){
            val instance = InventarRepos()
            INSTANCE = instance
            instance
        }
    }

    fun loadInventarData(invList: MutableLiveData<List<InventarData>>){
        invRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    val invList2 : List<InventarData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(InventarData::class.java)!!
                    }

                    invList.postValue(invList2)

                }catch(exc: java.lang.Exception){ }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}