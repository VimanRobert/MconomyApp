package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentEconNatBinding


class EconNatFragment : Fragment() {

    private lateinit var binding: FragmentEconNatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEconNatBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isAllFieldsChecked = false

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
        binding.calculeazaP.setOnClickListener {
            isAllFieldsChecked = CheckAllFields()
            if (isAllFieldsChecked) {
                if (binding.cheltuieliGuvid.text == null &&
                    binding.exporturiid.text != null &&
                    binding.importuriid.text != null &&
                    binding.investitiiid.text != null &&
                    binding.consumid.text != null ) {
                    binding.viewEconType.text = "** Economie privata"
                    binding.pibInput.text = (binding.consumid.text.toString().toDouble() + binding.investitiiid.text.toString().toDouble() +
                            (binding.exporturiid.text.toString().toDouble() - binding.importuriid.text.toString().toDouble())).toString()

                }else if (binding.exporturiid.text == null && binding.importuriid.text == null) {
                    binding.viewEconType.text = "** Economie inchisa"
                    binding.pibInput.text = (binding.consumid.text.toString().toDouble() + binding.investitiiid.text.toString().toDouble()).toString()
                }else if(binding.exporturiid.text != null &&
                         binding.importuriid.text != null &&
                         binding.investitiiid.text != null &&
                         binding.consumid.text != null &&
                         binding.cheltuieliGuvid.text != null){
                    binding.pibInput.text = (binding.consumid.text.toString().toDouble() + binding.investitiiid.text.toString().toDouble() + binding.cheltuieliGuvid.text.toString().toDouble() +
                            (binding.exporturiid.text.toString().toDouble() - binding.importuriid.text.toString().toDouble())).toString()
                    binding.viewEconType.text = "** Economie deschisa"
                }
            }
        }
    }
    private fun CheckAllFields(): Boolean {
        if (binding.consumid.length() == 0) {
            binding.consumid.error = "Camp obligatoriu !"
            return false
        }
        if (binding.investitiiid.length() == 0) {
            binding.investitiiid.error = "Camp obligatoriu !"
            return false
        }
        return true
    }
}