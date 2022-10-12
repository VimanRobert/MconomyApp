package com.example.mconomy.microdir

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentRentabilitateBinding


class RentabilitateFragment : Fragment() {

    private lateinit var binding: FragmentRentabilitateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRentabilitateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isAllFieldsChecked: Boolean



        // variabile de calcul
/*
        val profitNet: Double = binding.profitNetInput.text.toString().toDouble()
        val capitalPropriu: Double = binding.capitaluriInput.text.toString().toDouble()
        val activeTotale: Double = binding.activeTotaleInput.text.toString().toDouble()
        val dobanda: Double = binding.dobandaInput.text.toString().toDouble()
        val datorii: Double = binding.datoriiInput.text.toString().toDouble()

 */

        // variabile indicatori
/*
        val rentabilitateFinanciara : Double
        val rentabilitateaActiunilor : Double
        val rataDobanzii : Double

        rentabilitateFinanciara = profitNet / capitalPropriu
        rentabilitateaActiunilor = profitNet / activeTotale
        rataDobanzii = (dobanda / datorii).toString().toDouble()

 */

        binding.toRentChart.setOnClickListener {
            val bundle = Bundle()
            bundle.putFloat("keyRentFin", binding.rentFinanciaraText.text.toString().toFloat())
            bundle.putFloat("keyRentAct", binding.rentActiuniText.text.toString().toFloat())
            bundle.putFloat("keyRataDob", binding.rataDobanziiText.text.toString().toFloat())
            findNavController().navigate(R.id.action_rentabilitateFragment_to_statisticaRentabilitateFragment, bundle)


            // Thanks Razvan Ursu who helped me doing this implementation

        }

        binding.imageViewCapitaluri.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Suma tuturor capitalurilor")

            builder.setTitle("Capitaluri totale")
            builder.setCancelable(false)


            builder.setNeutralButton("Ok") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewActiveTotale.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Suma tuturor al activelor imobilizante, circulante si cheltuielilor in avans.")

            builder.setTitle("Active totale")
            builder.setCancelable(false)


            builder.setNeutralButton("Ok") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewProfitNet.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Diferenta dintre venit si cheltuieli")

            builder.setTitle("Profitul net")
            builder.setCancelable(false)


            builder.setNeutralButton("Ok") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewDatorii.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Suma tuturor datoriilor")

            builder.setTitle("Datorii")
            builder.setCancelable(false)


            builder.setNeutralButton("Ok") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewDobanda.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Dobanda acumulata")

            builder.setTitle("Dobanda")
            builder.setCancelable(false)


            builder.setNeutralButton("Ok") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        binding.calculRentabilitate.setOnClickListener {


            val profitNet: Double = binding.profitNetInput.text.toString().toDouble()
            val capitalPropriu: Double = binding.capitaluriInput.text.toString().toDouble()
            val activeTotale: Double = binding.activeTotaleInput.text.toString().toDouble()
            val dobanda: Double = binding.dobandaInput.text.toString().toDouble()
            val datorii: Double = binding.datoriiInput.text.toString().toDouble()

            val rentabilitateFinanciara: Double = profitNet / capitalPropriu
            val rentabilitateaActiunilor: Double = profitNet / activeTotale
            val rataDobanzii: Double = dobanda / datorii

            isAllFieldsChecked = checkTimeType()
            if (isAllFieldsChecked) {


                if (rentabilitateFinanciara > rentabilitateaActiunilor && rentabilitateaActiunilor > rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF > RA > RD\n Conditia pentru rentabilitate a fost indeplinita !"
                } else if (rentabilitateFinanciara > rentabilitateaActiunilor && rentabilitateaActiunilor == rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF > RA = RD"
                } else if (rentabilitateFinanciara == rentabilitateaActiunilor && rentabilitateaActiunilor == rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF = RA = RD"
                } else if (rentabilitateFinanciara == rentabilitateaActiunilor && rentabilitateaActiunilor > rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF = RA > RD"
                } else if (rentabilitateFinanciara < rentabilitateaActiunilor && rentabilitateaActiunilor < rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF < RA < RD"
                } else if (rentabilitateFinanciara < rentabilitateaActiunilor && rentabilitateaActiunilor == rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF < RA = RD"
                } else if (rentabilitateFinanciara < rentabilitateaActiunilor && rentabilitateaActiunilor > rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF < RA > RD"
                } else if (rentabilitateFinanciara > rentabilitateaActiunilor && rentabilitateaActiunilor < rataDobanzii) {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii\n RF > RA < RD"
                } else {
                    binding.indicatiiTextRentabilitate.text =
                        "Rent. Financiara : $rentabilitateFinanciara\nRent. Actiunilor : $rentabilitateaActiunilor\nRata dobanzii : $rataDobanzii"
                }
                binding.rentFinanciaraText.text =
                    rentabilitateFinanciara.toString()
                binding.rentActiuniText.text =
                    rentabilitateaActiunilor.toString()
                binding.rataDobanziiText.text = rataDobanzii.toString()

            }
            /*
            statisticaRentabilitateFragment.setArguments(savedInstanceState)
            savedInstanceState?.putString("keyRentFin", binding.rentFinanciaraText.text.toString())
            savedInstanceState?.putString("keyRentAct", binding.rentActiuniText.text.toString())
            savedInstanceState?.putString("keyRentFin", binding.rataDobanziiText.text.toString())

             */
        }
    }

    private fun checkTimeType(): Boolean {
        if (binding.profitNetInput.text.isEmpty()) {
            binding.profitNetInput.error = "Necesita completare!"
            return false
        }
        if (binding.capitaluriInput.text.isEmpty()) {
            binding.capitaluriInput.error = "Necesita completare!"
            return false
        }
        if (binding.activeTotaleInput.text.isEmpty()) {
            binding.activeTotaleInput.error = "Necesita completare!"
            return false
        }
        if (binding.dobandaInput.text.isEmpty()) {
            binding.dobandaInput.error = "Necesita completare!"
            return false
        }
        if (binding.datoriiInput.text.isEmpty()) {
            binding.datoriiInput.error = "Necesita completare!"
            return false
        }
        return true
    }
}