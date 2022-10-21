package com.example.mconomy.macrodir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentBancaBinding


class BancaFragment : Fragment() {

    private lateinit var binding: FragmentBancaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBancaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toImprumuturiBancare.setOnClickListener {
            findNavController().navigate(R.id.action_bancaFragment_to_imprumutBancarFragment)
        }
        binding.toCrediteBancare.setOnClickListener {
            findNavController().navigate(R.id.action_bancaFragment_to_crediteFragment)
        }
        binding.toProfitBancar.setOnClickListener {
            findNavController().navigate(R.id.action_bancaFragment_to_profitBancar)
        }
    }
}