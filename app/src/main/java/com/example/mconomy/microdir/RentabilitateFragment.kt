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

        binding.toRentChart.setOnClickListener {
            val bundle = Bundle()
            bundle.putFloat("keyRentFin", binding.rentFinanciaraText.text.toString().toFloat())
            bundle.putFloat("keyRentAct", binding.rentActiuniText.text.toString().toFloat())
            bundle.putFloat("keyRataDob", binding.rataDobanziiText.text.toString().toFloat())
            findNavController().navigate(
                R.id.action_rentabilitateFragment_to_statisticaRentabilitateFragment,
                bundle
            )
        }

        binding.imageViewCapitaluri.setOnClickListener {
            val builder = AlertDialog.Builder(context).apply {
                setMessage("Suma tuturor capitalurilor")

                setTitle("Capitaluri totale")
                setCancelable(false)
                setNeutralButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewActiveTotale.setOnClickListener {
            val builder = AlertDialog.Builder(context).apply {
                setMessage("Suma tuturor al activelor imobilizante, circulante si cheltuielilor in avans.")
                setTitle("Active totale")
                setCancelable(false)
                setNeutralButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewProfitNet.setOnClickListener {
            val builder = AlertDialog.Builder(context).apply {
                setMessage("Diferenta dintre venit si cheltuieli")
                setTitle("Profitul net")
                setCancelable(false)
                setNeutralButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewDatorii.setOnClickListener {
            val builder = AlertDialog.Builder(context).apply {
                setMessage("Suma tuturor datoriilor")
                setTitle("Datorii")
                setCancelable(false)
                setNeutralButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.imageViewDobanda.setOnClickListener {
            val builder = AlertDialog.Builder(context).apply {
                setMessage("Dobanda acumulata")
                setTitle("Dobanda")
                setCancelable(false)
                setNeutralButton("Ok") { dialog, _ ->
                    dialog.cancel()
                }
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
