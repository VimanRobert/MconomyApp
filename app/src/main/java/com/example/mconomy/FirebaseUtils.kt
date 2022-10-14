package com.example.mconomy

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseUtils : FirebaseMessagingService() {

    private lateinit var authProvider: FirebaseAuthProvider
    private lateinit var provide: com.example.mconomy.FirebaseUtils
    object FirebaseUtils {
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i("SellerFirebaseService ", "Refreshed token :: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        provide.sendRegistrationToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i("SellerFirebaseService ", "Message :: $message")
    }

    private fun changePassword(email: String): Boolean{


        return true
    }
}