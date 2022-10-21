package com.example.mconomy.microdir

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class SessionRepos {

    private lateinit var auth: FirebaseAuth
    //private val invRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Inventar").ref

    @Volatile private var instance2: SessionRepos?=null

    fun getInst() : SessionRepos{
        return instance2?: synchronized(this){
            val instance = SessionRepos()
            instance2 = instance
            instance
        }
    }

    fun loadInventarData(invList: MutableLiveData<List<SessionData>>){
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()
        val invRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Inventar/$uid").ref
        invRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try{
                    //val itemChildCount = dataSnapshot.childrenCount

                    val invList2 : List<SessionData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(SessionData::class.java)!!
                    }

                    invList.postValue(invList2)
                    //invRef.toString().substring(invRef.root.toString().length-1)
/*
                    for (snapshot in snapshot.getChildren()) {
                        val sessionPath = snapshot.child("produs").value.toString()
                    }

 */


                }catch(exc: java.lang.Exception){
                    Log.i("A aparut o eroare in procesul de inventariere", exc.message.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}