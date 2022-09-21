package com.example.mconomy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.FirebaseUtils.FirebaseUtils.firebaseAuth
import com.example.mconomy.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<EditText>(R.id.emailLOG)
        val password = view.findViewById<EditText>(R.id.passwordLOG)
        val loginID = view.findViewById<Button>(R.id.loginID)
        val toRegister = view.findViewById<Button>(R.id.toRegister)
        signInInputsArray = arrayOf(email, password)
        loginID.setOnClickListener {
            login()
        }
        toRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)

        }
    }

    private fun login(){

        signInEmail = emailLOG.text.toString().trim()
        signInPassword = passwordLOG.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        findNavController().navigate(R.id.action_loginFragment_to_firstInFragment)
                        Toast.makeText(context, "Conectat cu succes!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(
                            context,
                            "Conectarea a esuat! Reincercati din nou.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} necesita completare!"
                }
            }
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

}