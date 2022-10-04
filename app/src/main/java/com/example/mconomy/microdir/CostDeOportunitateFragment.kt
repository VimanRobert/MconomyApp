package com.example.mconomy.microdir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import com.example.mconomy.databinding.FragmentCostDeOportunitateBinding

class CostDeOportunitateFragment : Fragment() {

    private lateinit var binding: FragmentCostDeOportunitateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCostDeOportunitateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.variantePick.minValue = 2
        binding.variantePick.maxValue = 5

        binding.sortimentePick.minValue = 2
        binding.sortimentePick.maxValue = 5

        for (i in binding.sortimentePick) {
            for (j in binding.variantePick) {

            }
        }
    }
}