package com.example.mconomy


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mconomy.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val langArray = arrayOf("English", "Romana")
    private val email: String = auth.currentUser?.email.toString().trim()
    private val supportEmail = "robert.viman4@gmail.com".trim()

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


        //CERERE PENTRU SCHIMBAREA PAROLEI

        val subjectMessage = getString(R.string.ajutor)
        binding.buttonSchimbaParola.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(getString(R.string.esti_sigur))

            builder.setTitle(getString(R.string.schimba_parola))
            builder.setCancelable(true)


            builder.setNegativeButton(getString(R.string.nu)) { dialog, _ ->
                dialog.cancel()
            }
            builder.setPositiveButton(getString(R.string.da)) { dialog, _ ->
                dialog.apply {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener {
                        //if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            getString(R.string.mail_trimis),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    val builder2 = AlertDialog.Builder(context)
                    builder2.setMessage(getString(R.string.ramai_conectat_ask))

                    builder2.setTitle("Cont")
                    builder2.setCancelable(true)


                    builder2.setNegativeButton(R.string.nu) { _, _ ->
                        auth.signOut()
                        val intent = Intent(activity, MainActivity::class.java)
                        activity?.startActivity(intent)
                    }
                    builder2.setPositiveButton(R.string.da) { dialog, _ ->
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

        subjectMessage.trim()
        supportEmail.split(",".toRegex()).toTypedArray().toString().trim()
        binding.buttonTrimiteMesajul.setOnClickListener {
            sendMailToSupport()
        }
        val langAdapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, langArray)
        langAdapter.setDropDownViewResource(android.R.layout.simple_gallery_item)
        val setLang = binding.schimbaLimba
        setLang.adapter = langAdapter
        if(binding.buttonTrimiteMesajul.text.toString() == "Trimite")
            setLang.setSelection(1)
        else
            setLang.setSelection(0)

        //STERGEREA MESAJULUI

        binding.stergeMesajul.setOnClickListener {
            val mesaj = binding.suportMessageText.text
            if(mesaj.isNotEmpty()){
                mesaj.clear()
            }else if(mesaj.isEmpty()) {
                Toast.makeText(context, getString(R.string.text_gol), Toast.LENGTH_SHORT)
            }
        }

        //SELECTEAZA LIMBA

        setLang.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val select = parent?.getItemAtPosition(position).toString()
                if (select == "English") {
                    //setLocalLanguage(MainActivity(), "en")
                    setLocalLanguage("en")
                    //setCurrentFragment(SettingsFragment())

                } else if (select == "Romana") {
                    //setLocalLanguage(MainActivity(), "ro")
                    setLocalLanguage("ro")
                    //setCurrentFragment(SettingsFragment())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


    }

    fun setLocalLanguage(lang: String) {
        val locale = Locale(lang)
        locale.getDisplayLanguage(locale)
        val resources: Resources = resources
        val config: Configuration = resources.configuration
        val metrics: DisplayMetrics? = resources.displayMetrics
        config.setLocale(locale)
        @Suppress("DEPRECATION")
        resources.updateConfiguration(config, metrics)
        //resources.configuration.locales
        onConfigurationChanged(config)
    }

    @SuppressLint("IntentReset")
    fun sendMailToSupport() {
        val subjectMessage = getString(R.string.ajutor)
        val message = binding.suportMessageText.text.toString().trim()
        if (message.isEmpty()) {
            Toast.makeText(context, getString(R.string.text_gol), Toast.LENGTH_SHORT).show()
            binding.suportMessageText.error = getString(R.string.err_no_text_send_mail)
        } else {
            auth.currentUser?.email?.split(",".toRegex())?.toTypedArray()
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("mailto:")
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_CC, supportEmail)
            intent.putExtra(Intent.EXTRA_SUBJECT, subjectMessage)
            intent.putExtra(Intent.EXTRA_TEXT, message)

            try {
                startActivity(Intent.createChooser(intent, getString(R.string.alege_aplicatia)))
                //Toast.makeText(context, "Mesajul a fost trimis cu succes !", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    getString(R.string.nu_exista_app_comp),
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host,fragment)
            commit()
        }

}
