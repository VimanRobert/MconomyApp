package com.example.mconomy.microdir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentRentabilitateBinding


class RentabilitateFragment : Fragment() {

    private lateinit var binding: FragmentRentabilitateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRentabilitateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rentToMacro.setOnClickListener {
            findNavController().navigate(R.id.action_rentabilitateFragment_to_microFragment)
        }

    }
}