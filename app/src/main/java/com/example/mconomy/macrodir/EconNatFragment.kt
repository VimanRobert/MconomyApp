package com.example.mconomy.macrodir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentEconNatBinding
import kotlinx.android.synthetic.main.fragment_econ_nat.*


class EconNatFragment : Fragment() {
    private lateinit var binding: FragmentEconNatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEconNatBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculeazaP.setOnClickListener{
            if (binding.cheltuieliGuvid.text == null){
                binding.viewEconType.text = "** Economie privata"
            }else if(binding.exporturiid.text == null){
                binding.viewEconType.text = "** Economie inchisa"
            }else if (binding.consumid == null){
                consumid.error = "Este camp obligatoriu !"
            }
            else if (binding.investitiiid == null){
                investitiiid.error = "Este camp obligatoriu !"
            }
        }

    }
}