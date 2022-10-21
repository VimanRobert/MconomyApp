package com.example.mconomy

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
//import android.content.pm.PackageManager
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
    //private  var packageManager: PackageManager?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("ShowToast", "IntentReset")
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

        val subjectMessage ="Ajutor"
        subjectMessage.trim()
        supportEmail.split(",".toRegex()).toTypedArray().toString().trim()
        binding.buttonTrimiteMesajul.setOnClickListener {
            val message = binding.suportMessageText.text.toString().trim()
            if (message.isEmpty()) {
                Toast.makeText(context, "Textul este gol !", Toast.LENGTH_SHORT).show()
                binding.suportMessageText.error = "Pentru a trimite un mail catre centrul de suport este nevoie sa introduci un mesaj aici !"
            } else {
                auth.currentUser?.email?.split(",".toRegex())?.toTypedArray()
                val intent = Intent(Intent.ACTION_SEND)
                intent.data = Uri.parse("mailto:")
                intent.type = "message/rfc822"
                intent.putExtra(Intent.EXTRA_EMAIL, email)
                intent.putExtra(Intent.EXTRA_SUBJECT, subjectMessage)
                intent.putExtra(Intent.EXTRA_TEXT, message)

                try {
                    startActivity(Intent.createChooser(intent, "Alege aplicatia"))
                    //Toast.makeText(context, "Mesajul a fost trimis cu succes !", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Nu exista o aplicatie care sa deschida mesageria! Descarca una de pe Magazin Play (ex: Gmail, Yahoo Mail)",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

    }
}
