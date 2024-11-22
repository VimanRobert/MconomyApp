package com.example.mconomy.conectivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.conectivity.firebase.FirebaseUtils.FirebaseUtils.firebaseAuth
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentRegisterBinding
//import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*


@SuppressLint("CheckResult")
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var toast: Toast
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etEmail = view.findViewById<EditText>(R.id.emailREG)
        val etPassword = view.findViewById<EditText>(R.id.passwordREG)
        val etConfirmPassword = view.findViewById<EditText>(R.id.passwordConfirm)

        createAccountInputsArray = arrayOf(etEmail, etPassword, etConfirmPassword)

        val registerAccount = view.findViewById<Button>(R.id.registerID)
        registerAccount.setOnClickListener {
            signIn()
        }
    }

    private fun notEmpty(): Boolean = emailREG.text.toString().trim().isNotEmpty() &&
            passwordREG.text.toString().trim().isNotEmpty() &&
            passwordConfirm.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            passwordREG.text.toString().trim() == passwordConfirm.text.toString().trim()
        ) {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast = Toast.makeText(context, "Parolele nu se potrivesc!", Toast.LENGTH_SHORT)
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            userEmail = emailREG.text.toString().trim()
            userPassword = passwordREG.text.toString().trim()

            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Contul a fost creat cu succes!",
                            Toast.LENGTH_SHORT
                        ).show()
                        //sendEmailVerification()
                    } else {
                        Toast.makeText(
                            context,
                            "Autentificarea a esuat!\nMai incearca",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}
