package com.example.mconomy.microdir

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class InventarRepos {
    private val invRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Inventar")

    @Volatile
    private var instance2: InventarRepos? = null

    fun getInst(): InventarRepos {
        return instance2 ?: synchronized(this) {
            val instance = InventarRepos()
            instance2 = instance
            instance
        }
    }

    fun loadInventarData(invList: MutableLiveData<List<InventarData>>) {
        invRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val invList2: List<InventarData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(InventarData::class.java)!!
                    }

                    invList.postValue(invList2)

                } catch (exc: java.lang.Exception) {
                    Log.i("A aparut o eroare in procesul de inventariere", exc.message.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}