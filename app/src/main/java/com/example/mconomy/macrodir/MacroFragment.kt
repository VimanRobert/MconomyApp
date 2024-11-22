package com.example.mconomy.macrodir

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
        binding = FragmentMacroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            banca.setOnClickListener {
                findNavController().navigate(R.id.action_macroFragment_to_bancaFragment)
            }

            econNat.setOnClickListener {
                findNavController().navigate(R.id.action_macroFragment_to_econNatFragment)
            }

            actiuni.setOnClickListener {
                findNavController().navigate(R.id.action_macroFragment_to_actiuniFragment)
            }

            infoMacro.setOnClickListener {
                findNavController().navigate(R.id.action_macroFragment_to_infoMacroFragment)
            }

            backtomainMacro.setOnClickListener {
                findNavController().navigate(R.id.action_macroFragment_to_firstInFragment)
            }
        }

        val topBarText = activity?.findViewById<TextView>(R.id.topbartext)
        topBarText?.text = getString(R.string.macroeconomie)
    }
}
