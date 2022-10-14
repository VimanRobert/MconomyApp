package com.example.mconomy

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mconomy.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var auth: FirebaseAuth
    private  var packageManager: PackageManager?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val email: String = auth.currentUser?.email.toString().trim()
        val supportEmail = "robert.viman4@gmail.com"

        //CERERE PENTRU SCHIMBAREA PAROLEI

        binding.buttonSchimbaParola.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Esti sigur ?")

            builder.setTitle("Schimba parola")
            builder.setCancelable(true)


            builder.setNegativeButton("Nu") { dialog, _ ->
                dialog.cancel()
            }
            builder.setPositiveButton("Da") { dialog, _ ->
                dialog.apply {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener {
                        //if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Emailul a fost trimis! verifica mailbox-ul.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                            val builder2 = AlertDialog.Builder(context)
                            builder2.setMessage("Ramai conectat ?")

                            builder2.setTitle("Cont")
                            builder2.setCancelable(true)


                            builder2.setNegativeButton("Nu") { _, _ ->
                                auth.signOut()
                                val intent = Intent(activity, MainActivity::class.java)
                                activity?.startActivity(intent)
                            }
                            builder2.setPositiveButton("Da"){ dialog, _ ->
                                dialog.cancel()
                            }
                            val alertDialog2 = builder2.create()
                            alertDialog2.show()
                }
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }

        //TRIMITEREA UNUI MESAJ CATRE SUPPORT TEAM

        binding.buttonTrimiteMesajul.setOnClickListener {
            val message = binding.suportMessageText.text.toString()
            auth.currentUser?.email?.split(",".toRegex())?.toTypedArray()
            val intent =Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, supportEmail)
                putExtra(Intent.EXTRA_SUBJECT, "Ajutor")
                putExtra(Intent.EXTRA_TEXT, message)
            }
            if(packageManager?.let { it1 -> intent.resolveActivity(it1) } != null){
                startActivity(intent)
            }else{
                Toast.makeText(context, "Nu exista o aplicatie care sa deschida mesageria! Descarca una de pe Magazin Play (ex: Gmail, Yahoo Mail)", Toast.LENGTH_LONG).show()
            }

        }
    }
}
