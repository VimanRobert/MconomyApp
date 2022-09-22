package com.example.mconomy

import android.content.ClipData
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.session.MediaSession
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.mconomy.FirebaseUtils.Extensions.toast
import com.example.mconomy.FirebaseUtils.FirebaseUtils.firebaseAuth
import com.example.mconomy.databinding.ActivityMainBinding
import com.example.mconomy.databinding.FragmentFirstInBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseTools: FirebaseAnalytics
    private lateinit var token: MediaSession.Token
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firstInFragment: FirstInFragment
    //private lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        //auth = Firebase.auth
/*
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "getInstanceId failed", task.exception)
                return@OnCompleteListener
            }

            // Get new Instance ID token
            val token = task.result

            Log.d(TAG, token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

 */
        binding.topBar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.action_logout-> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }


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

    private companion object{
        private const val TAG = "MainActivity"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_topbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_logout){
            Log.i(TAG, "Logout")
            auth.signOut()
            val logoutIntent = Intent(this, MainActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)
        }
        return super.onOptionsItemSelected(item)
    }


}