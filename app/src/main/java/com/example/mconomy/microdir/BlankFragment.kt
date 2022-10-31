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

    private lateinit var binding: FragmentInventarBinding
    private lateinit var database: DatabaseReference
    private lateinit var database2: DatabaseReference
    private lateinit var auth: FirebaseAuth


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

        //val nrSesiune = view.findViewById<TextView>(R.id.nr_sesiune_input)
        //val dataSesiune = view.findViewById<TextView>(R.id.data_sesiune_input)

        var isAllFieldsChecked: Boolean

        val currentDate = LocalDateTime.now()
        val formatDate = DateTimeFormatter.ofPattern("HH:mm_dd-MM-yy")
        val formattedData = currentDate.format(formatDate)

        val currentDate2 = LocalDateTime.now()
        val formatDate2 = DateTimeFormatter.ofPattern("dd-MM-yy")
        val formattedData2 = currentDate2.format(formatDate2)

        var countItems = 0

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
                val uid = auth.currentUser?.uid.toString()
                //val userEmail = auth.currentUser?.email.toString()

                val produsB = binding.numeProdusID.text.toString()
                val pretB = binding.pretID.text.toString().toDouble()
                val cantitateB = binding.cantitateID.text.toString().toDouble()
                val rezcalculB = binding.rezInitial.text.toString().toDouble()
                val reztotalB = binding.rezTotal.text.toString().toDouble()
                val sesiuneB = binding.nrSesiune.text.toString().toInt()
                //nrSesiune.text = sesiuneB.toString()
                //dataSesiune.text = formattedData.toString()

                //if (uid.isNotEmpty()) {


                //var inv_number = 0
                database = Firebase.database.getReference("Inventar/$uid/Sesiunea_$sesiuneB-$formattedData2/Produse")


                val inventar = InventarData(produsB, pretB, cantitateB, rezcalculB, reztotalB)
                database.child(produsB).setValue(inventar).addOnSuccessListener {
                    countItems++
                    val addSession = SessionData(sesiuneB, countItems, reztotalB, formattedData)
                    database2 = Firebase.database.getReference("Inventar/$uid/Sesiunea_$sesiuneB-$formattedData2")
                    database2.child("Info").setValue(addSession)
                    produs.text = null
                    pret.text = null
                    cantitate.text = null
                    rezCalc.text = null

                    Toast.makeText(context, getString(R.string.adaugat_cu_succes), Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        context,
                        getString(R.string.eroare_conexiune),
                        Toast.LENGTH_SHORT
                    ).show()
                    //}
                }
            }
        }

        binding.saveInventar.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setMessage(getString(R.string.salveaza_inventar) + " $formattedData ?")

            if (binding.rezTotal.text.toString().toDouble() == 0.0) {
                Toast.makeText(
                    context,
                    getString(R.string.inventar_gol),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                builder.setTitle(getString(R.string.salvare_inventar_title))
                builder.setCancelable(true)


                builder.setPositiveButton(getString(R.string.confirma)) { dialog, _ ->
                    dialog.apply {
                        //database = Firebase.database.getReference("Inventar/Sesiunea_$sesiune-$formattedData")
                        Toast.makeText(
                            context,
                            getString(R.string.sesiune_salvata)+"\n$formattedData",
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
                builder.setNegativeButton(getString(R.string.anulare)) { dialog, _ ->
                    dialog.cancel()
                }

                val alertDialog = builder.create()
                alertDialog.show()

            }
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.nrSesiune.length() == 0) {
            binding.nrSesiune.error = getString(R.string.camp_obligatoriu)
            return false
        }
        if (binding.numeProdusID.length() == 0) {
            binding.numeProdusID.error = getString(R.string.camp_obligatoriu)
            return false
        }
        if (binding.pretID.length() == 0) {
            binding.pretID.error = getString(R.string.camp_obligatoriu)
            return false
        }
        if (binding.cantitateID.length() == 0) {
            binding.cantitateID.error = getString(R.string.camp_obligatoriu)
            return false
        }
        return true
    }
}