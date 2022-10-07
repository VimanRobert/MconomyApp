package com.example.mconomy.microdir

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
        val sesiune = view.findViewById<EditText>(R.id.nr_sesiune)
        var isAllFieldsChecked: Boolean

        val currentDate = LocalDateTime.now()
        val formatDate = DateTimeFormatter.ofPattern("HH:mm_dd-MM-yy")
        val formattedData = currentDate.format(formatDate)

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
                val sesiuneB = binding.nrSesiune.text.toString().toInt()


                //if (uid.isNotEmpty()) {


                //var inv_number = 0
                database = Firebase.database.getReference("Inventar/Sesiunea_$sesiuneB-$formattedData")


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


            val builder = AlertDialog.Builder(context)
            builder.setMessage("Doriti sa salvati sesiunea sesiunea cu data $formattedData ?")

            builder.setTitle("Salvare sesiune de inventar")
            builder.setCancelable(true)


            builder.setPositiveButton("Confirma") { dialog, _ ->
                dialog.apply {
                    database = Firebase.database.getReference("Inventar/Sesiunea_$sesiune-$formattedData")
                    Toast.makeText(
                        context,
                        "Sesiunea a fost salvata !\n$formattedData",
                        Toast.LENGTH_SHORT
                    ).show()
                    produs.text = null
                    pret.text = null
                    cantitate.text = null
                    rezCalc.text = null
                    rezTotal.text = null
                    sesiune.text = null
                }
            }
            builder.setNegativeButton("Anuleaza") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()

        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.nrSesiune.length() == 0) {
            binding.nrSesiune.error = "Camp obligatoriu !"
            return false
        }
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