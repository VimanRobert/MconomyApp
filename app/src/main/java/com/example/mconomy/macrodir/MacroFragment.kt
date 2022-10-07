package com.example.mconomy.macrodir


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentMacroBinding

class MacroFragment : Fragment() {
    private lateinit var binding: FragmentMacroBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMacroBinding.inflate(inflater, container ,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_macro, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.banca.setOnClickListener {
            findNavController().navigate(R.id.action_macroFragment_to_bancaFragment)
        }

        binding.econNat.setOnClickListener{
            findNavController().navigate(R.id.action_macroFragment_to_econNatFragment)
        }

        binding.actiuni.setOnClickListener {
            findNavController().navigate(R.id.action_macroFragment_to_actiuniFragment)
        }

        binding.infoMacro.setOnClickListener {
            findNavController().navigate(R.id.action_macroFragment_to_infoMacroFragment)
        }

        binding.backtomainMacro.setOnClickListener{
            findNavController().navigate(R.id.action_macroFragment_to_firstInFragment)
        }
        val topBarText = activity?.findViewById<TextView>(R.id.topbartext)
        topBarText?.text = "Macroeconomie"
    }
}