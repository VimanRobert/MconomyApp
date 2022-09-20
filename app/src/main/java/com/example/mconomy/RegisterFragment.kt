package com.example.mconomy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.FirebaseUtils.FirebaseUtils.firebaseAuth
import com.example.mconomy.FirebaseUtils.FirebaseUtils.firebaseUser
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_register.*


@SuppressLint("CheckResult")
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var toast: Toast
    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>

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
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        /*
                       Alter methods !!!!

        val emailValid = RxTextView.textChanges(binding.username).skipInitialValue().map {
            email -> !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        emailValid.subscribe{
            showAlertEmail(it)
        }
        val passwordValid = RxTextView.textChanges(binding.password).skipInitialValue().map {
                passw -> passw.length < 8
        }
        passwordValid.subscribe{
            showAlertPassword(it)
        }
        val confPassword = Observable.merge(RxTextView.textChanges(binding.password)
            .skipInitialValue().map { passw ->
                passw.toString() != binding.passwordConfirm.text.toString() },
            RxTextView.textChanges(binding.passwordConfirm)
                .skipInitialValue().map { passwConf ->
                    passwConf.toString() != binding.password.text.toString()})

        val invalidFields = Observable.combineLatest(emailValid, passwordValid, confPassword, {
            emailInv: Boolean, passwordInv: Boolean, passConfInv: Boolean ->
            !emailInv && !passwordInv && !passConfInv
        })
        invalidFields.subscribe{
                  isValid ->
            if(isValid){
                binding.registerID.isEnabled = true
                binding.registerID.backgroundTintList =
                    context?.let { ContextCompat.getColorStateList(it, R.color.colorAccent) }
        }
        }


        val emailX = binding.username.findViewById<EditText>(R.id.username).toString()
        val passwordX = binding.password.findViewById<EditText>(R.id.password).toString()

        binding.registerID.setOnClickListener{
            registerUser(emailX, passwordX)
        }

    }
    private fun showAlertEmail(isNotValid: Boolean){
        binding.username.error = if (isNotValid) "Email invalid sau deja existent !" else null

    }
    private fun showAlertPassword(isNotValid: Boolean){
        binding.password.error = if (isNotValid) "Parola invalida sau deja existenta !" else null

    }
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    Toast.makeText(context,"You are now registered!",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,it.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }

         */
    }

    private fun notEmpty(): Boolean = emailREG.text.toString().trim().isNotEmpty() &&
            passwordREG.text.toString().trim().isNotEmpty() &&
            passwordConfirm.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            passwordREG.text.toString().trim() == passwordConfirm.text.toString().trim()
        ) {
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
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = emailREG.text.toString().trim()
            userPassword = passwordREG.text.toString().trim()

            /*create a user*/
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
/*
    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Mailul pentru verificare a fost trimis catre $emailREG",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

 */
}