package com.example.mconomy


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mconomy.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.topBar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.action_logout-> {
                    auth.signOut()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }
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