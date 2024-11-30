package com.example.mconomy.conectivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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

@SuppressLint("CheckResult")
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var toast: Toast
    private lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerID.setOnClickListener {
            signIn(
                binding.emailREG.text.toString(),
                binding.passwordREG.text.toString(),
                binding.passwordConfirm.text.toString()
            )
        }
    }

    private fun identicalPassword(password: String, confirmPassword: String): Boolean {
        Log.i(
            "TAG",
            "${binding.emailREG.text}, ${binding.passwordREG.text}, ${binding.passwordConfirm.text}"
        )
        var identical = false
        if (password.isNotEmpty() && confirmPassword.isNotEmpty() && confirmPassword == password
        ) {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            identical = true
        } else if (password.isEmpty() || confirmPassword.isEmpty()) {
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

    private fun signIn(email: String, password: String, confirmPassword: String) {
        Log.i(
            "TAG",
            "$email, $password, $confirmPassword"
        )
        if (identicalPassword(password, confirmPassword)) {
            email.trim()
            password.trim()
            confirmPassword.trim()

            firebaseAuth.createUserWithEmailAndPassword(email, password)
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
        } else {
            Toast.makeText(
                context,
                "Parolele nu se potrivesc!\nMai incearca",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
