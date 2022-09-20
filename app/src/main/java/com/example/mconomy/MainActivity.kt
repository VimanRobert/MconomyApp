package com.example.mconomy

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mconomy.FirebaseUtils.Extensions.toast
import com.example.mconomy.FirebaseUtils.FirebaseUtils.firebaseAuth
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.analytics.FirebaseAnalytics


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseTools: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val topBar = findViewById<Toolbar>(R.id.topBar)
        val userID = intent.getStringExtra("user_id")
        val emailID = intent.getStringExtra("email_id")

        /*
        val logout = findViewById<Button>(R.id.loginID)

        firebaseTools = FirebaseAnalytics.getInstance(this)
        logout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            toast("Deconectat")
            finish()
        }

         */
/*
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity,
            OnSuccessListener<Any> { instanceIdResult ->
                val token: String = instanceIdResult.getToken()
            })

 */
        /*
        logoutButton.estOnClickListener{
            FirebaseAuth.getInstance().singOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java)
            finish()
            }
         */
    }
}