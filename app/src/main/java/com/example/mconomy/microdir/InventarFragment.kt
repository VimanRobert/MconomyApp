package com.example.mconomy.microdir

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentInventarBinding
import com.example.mconomy.microdir.InventarData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BlankFragment : Fragment() {

    private lateinit var dataInv: InventarData
    private lateinit var binding: FragmentInventarBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInventarBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var produs = view.findViewById<EditText>(R.id.numeProdusID)
        var pret = view.findViewById<EditText>(R.id.pretID)
        var cantitate = view.findViewById<EditText>(R.id.cantitateID)

        var rezCalc = view.findViewById<TextView>(R.id.rezInitial)
        var rezTotal = view.findViewById<TextView>(R.id.rezTotal)
        var isAllFieldsChecked = false


        val calcul = view.findViewById<Button>(R.id.calcul)
        calcul.setOnClickListener {
            isAllFieldsChecked = CheckAllFields()
            if(isAllFieldsChecked){
                rezCalc.text = (pret.text.toString().toDouble() * cantitate.text.toString()
                    .toDouble()).toString()
                rezTotal.text = (rezTotal.text.toString().toDouble() + rezCalc.text.toString()
                    .toDouble()).toString()
            }
        }


        binding.addProdus.setOnClickListener(){

            val produsB = binding.numeProdusID.text.toString()
            val pretB = binding.pretID.text.toString().toDouble()
            val cantitateB = binding.cantitateID.text.toString().toDouble()
            val rezcalculB = binding.rezInitial.text.toString().toDouble()
            val reztotalB = binding.rezTotal.text.toString().toDouble()

            database = Firebase.database.getReference("Inventar")
            val Inventar = InventarData(produsB, pretB, cantitateB, rezcalculB, reztotalB)
            database.child(produsB).setValue(Inventar).addOnSuccessListener {
                produs.text = null
                pret.text = null
                cantitate.text = null
                rezCalc.text = null

                Toast.makeText(context, "Adaugat cu succes !", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(context, "A aparut o problema ! Verifica conexiunea la internet", Toast.LENGTH_SHORT).show()
            }
        }

        binding.saveInventar.setOnClickListener{

        }

        binding.todatabase.setOnClickListener{

        }
    }
    private fun CheckAllFields(): Boolean {
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