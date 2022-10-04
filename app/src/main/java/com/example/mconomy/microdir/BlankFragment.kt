package com.example.mconomy.microdir

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentInventarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BlankFragment : Fragment() {

    //private lateinit var dataInv: InventarData
    private lateinit var binding: FragmentInventarBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventarBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val produs = view.findViewById<EditText>(R.id.numeProdusID)
        val pret = view.findViewById<EditText>(R.id.pretID)
        val cantitate = view.findViewById<EditText>(R.id.cantitateID)

        val rezCalc = view.findViewById<TextView>(R.id.rezInitial)
        val rezTotal = view.findViewById<TextView>(R.id.rezTotal)
        var isAllFieldsChecked: Boolean


        binding.calcul.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked) {
                rezCalc.text = (pret.text.toString().toDouble() * cantitate.text.toString()
                    .toDouble()).toString()
                rezTotal.text = (rezTotal.text.toString().toDouble() + rezCalc.text.toString()
                    .toDouble()).toString()
            }
        }


        binding.addProdus.setOnClickListener {

            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked) {

                auth = FirebaseAuth.getInstance()
                uid = auth.currentUser?.uid.toString()

                val produsB = binding.numeProdusID.text.toString()
                val pretB = binding.pretID.text.toString().toDouble()
                val cantitateB = binding.cantitateID.text.toString().toDouble()
                val rezcalculB = binding.rezInitial.text.toString().toDouble()
                val reztotalB = binding.rezTotal.text.toString().toDouble()

                val numarSesiune = binding.nrSesiune.text.toString()
                //if (uid.isNotEmpty()) {


                //var inv_number = 0
                database = Firebase.database.getReference("Inventar/Sesiunea_$numarSesiune")


                val inventar = InventarData(produsB, pretB, cantitateB, rezcalculB, reztotalB)
                database.child(produsB).setValue(inventar).addOnSuccessListener {
                    produs.text = null
                    pret.text = null
                    cantitate.text = null
                    rezCalc.text = null

                    Toast.makeText(context, "Adaugat cu succes !", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "A aparut o problema ! Verifica conexiunea la internet",
                        Toast.LENGTH_SHORT
                    ).show()
                    //}
                }
            }
        }

        binding.saveInventar.setOnClickListener {

        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.numeProdusID.length() == 0) {
            binding.numeProdusID.error = "Camp obligatoriu !"
            return false
        }
        if (binding.pretID.length() == 0) {
            binding.pretID.error = "Camp obligatoriu !"
            return false
        }
        if (binding.cantitateID.length() == 0) {
            binding.cantitateID.error = "Camp obligatoriu !"
            return false
        }
        return true
    }
}