package com.example.mconomy.microdir

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class InventarRepos {
    private lateinit var auth: FirebaseAuth

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
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()
        val allProducts: MutableList<InventarData> = mutableListOf()
        val invRef: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Inventar/$uid").ref

        invRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    for (sessionSnapshot in snapshot.children) {
                        val sessionId = sessionSnapshot.key

                        if (sessionId != null) {
                            val produseRef = sessionSnapshot.child("Produse")

                            for (productSnapshot in produseRef.children) {
                                val produs =
                                    productSnapshot.child("produs").getValue(String::class.java)
                                val cantitate =
                                    productSnapshot.child("cantitate").getValue(Double::class.java)
                                val pret =
                                    productSnapshot.child("pret").getValue(Double::class.java)
                                val rez = productSnapshot.child("rez").getValue(Double::class.java)
                                val rezTotal =
                                    productSnapshot.child("rezTotal").getValue(Double::class.java)

                                if (produs != null && cantitate != null && pret != null && rez != null && rezTotal != null) {
                                    allProducts.add(
                                        InventarData(
                                            produs,
                                            cantitate,
                                            pret,
                                            rez,
                                            rezTotal
                                        )
                                    )
                                }
                            }
                        }
                    }
                    invList.postValue(allProducts)

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
