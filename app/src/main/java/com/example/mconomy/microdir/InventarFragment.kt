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

class BlankFragment : Fragment() {

    private lateinit var dataInv: InventarData
    private lateinit var binding: FragmentInventarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInventarBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_inventar, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var produs = view.findViewById<EditText>(R.id.numeProdusID)
        var pret = view.findViewById<EditText>(R.id.pretID)
        var cantitate = view.findViewById<EditText>(R.id.cantitateID)

        var rezCalc = view.findViewById<TextView>(R.id.rezInitial)
        var rezTotal = view.findViewById<TextView>(R.id.rezTotal)


        val calcul = view.findViewById<Button>(R.id.calcul)
        calcul.setOnClickListener {
            rezCalc.text = (pret.text.toString().toDouble() * cantitate.text.toString().toDouble()).toString()
            rezTotal.text = (rezTotal.text.toString().toDouble() + rezCalc.text.toString().toDouble()).toString()

        }

        val addProdus = view.findViewById<Button>(R.id.addProdus)
        addProdus.setOnClickListener(){
            produs.text = null
            pret.text = null
            cantitate.text = null
            rezCalc.text = null
            Toast.makeText(context, "Adaugat cu succes !", Toast.LENGTH_SHORT).show()
        }
        val saveInventar = view.findViewById<Button>(R.id.saveInventar)
        saveInventar.setOnClickListener{

        }
        val todatabase = view.findViewById<Button>(R.id.todatabase)
        todatabase.setOnClickListener{

        }
    }
}