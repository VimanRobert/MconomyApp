package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentEconNatBinding


class EconNatFragment : Fragment() {

    private lateinit var binding: FragmentEconNatBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEconNatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isAllFieldsChecked: Boolean

/*
        var PIB = binding.pibInput.text
        var PNB = binding.pnbInput.text
        var PGB = binding.pgbInput.text

        var consum = binding.consumid.text.toString().toDouble()
        var investitii = binding.investitiiid.text.toString().toDouble()
        var exporturi = binding.exporturiid.text.toString().toDouble()
        var importuri = binding.importuriid.text.toString().toDouble()
        var expNet = exporturi - importuri
        var chGuvern = binding.cheltuieliGuvid.text.toString().toDouble()
        var PIB = binding.pibInput.text
        var PNB = binding.pnbInput.text
        var PGB = binding.pgbInput.text

 */


        binding.toExIndicatori.setOnClickListener {
            findNavController().navigate(R.id.action_econNatFragment_to_exempleIndicatoriFragment)
        }

        binding.calculeazaP.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked) {
                if (binding.cheltuieliGuvid.text!!.isEmpty() &&
                    binding.exporturiid.text != null &&
                    binding.importuriid.text != null &&
                    binding.investitiiid.text != null &&
                    binding.consumid.text != null
                ) {
                    binding.pibInput.text = (binding.consumid.text.toString()
                        .toDouble() + binding.investitiiid.text.toString().toDouble() +
                            (binding.exporturiid.text.toString()
                                .toDouble() - binding.importuriid.text.toString()
                                .toDouble())).toString()

                    binding.pnbInput.text = (binding.pibInput.text.toString()
                        .toDouble() + binding.venitRezDinStrainatate.text.toString()
                        .toDouble() - binding.venitRezStraini.text.toString().toDouble()).toString()
                    if (binding.calculeazaP.text.toString() != "Calculeaza"){
                        binding.viewEconType.text = "** Private economy"
                    }else
                    binding.viewEconType.text = "** Economie privata"

                } else if (binding.exporturiid.text!!.isEmpty() && binding.importuriid.text!!.isEmpty()) {
                    if(binding.calculeazaP.text.toString() != "Calculeaza"){
                        binding.viewEconType.text = "** Economie inchisa (PIB = PNB)"
                    }else
                    binding.viewEconType.text = "** Closed economy (GDP = GNP)"
                    binding.pibInput.text = (binding.consumid.text.toString()
                        .toDouble() + binding.investitiiid.text.toString()
                        .toDouble() + binding.cheltuieliGuvid.text.toString().toDouble()).toString()
                    binding.pnbInput.text = binding.pibInput.text
                } else if (binding.exporturiid.text != null &&
                    binding.importuriid.text != null &&
                    binding.investitiiid.text != null &&
                    binding.consumid.text != null &&
                    binding.cheltuieliGuvid.text != null
                ) {
                    binding.pibInput.text = (binding.consumid.text.toString()
                        .toDouble() + binding.investitiiid.text.toString()
                        .toDouble() + binding.cheltuieliGuvid.text.toString().toDouble() +
                            (binding.exporturiid.text.toString()
                                .toDouble() - binding.importuriid.text.toString()
                                .toDouble())).toString()

                    binding.pnbInput.text = (binding.pibInput.text.toString()
                        .toDouble() + binding.venitRezDinStrainatate.text.toString()
                        .toDouble() - binding.venitRezStraini.text.toString().toDouble()).toString()
                    if(binding.calculeazaP.text.toString() != "Calculeaza"){
                        binding.viewEconType.text = "** Open econmomy"
                    }else
                    binding.viewEconType.text = "** Economie deschisa"
                }
            }
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.consumid.length() == 0) {
            binding.consumid.error = getString(R.string.camp_obligatoriu)
            return false
        }
        if (binding.investitiiid.length() == 0) {
            binding.investitiiid.error = getString(R.string.camp_obligatoriu)
            return false
        }
        if (binding.consumid.length() == 0 || binding.investitiiid.length() == 0) {
            binding.consumid.error = getString(R.string.camp_obligatoriu)
            binding.investitiiid.error = getString(R.string.camp_obligatoriu)
            return false
        }
        return true
    }
}