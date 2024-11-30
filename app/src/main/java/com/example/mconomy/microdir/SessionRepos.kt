package com.example.mconomy.microdir

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SessionRepos {
    private lateinit var auth: FirebaseAuth

    @Volatile
    private var instance2: SessionRepos? = null

    fun getInst(): SessionRepos {
        return instance2 ?: synchronized(this) {
            val instance = SessionRepos()
            instance2 = instance
            instance
        }
    }

    fun loadInventarData(invList: MutableLiveData<List<SessionData>>) {
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()
        val invRef: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Inventar/$uid").ref

        invRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val invList2: List<SessionData> = snapshot.children.mapNotNull { dataSnapshot ->
                        val infoSnapshot = dataSnapshot.child("Info")
                        infoSnapshot.getValue(SessionData::class.java)
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
