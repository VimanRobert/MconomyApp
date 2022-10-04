package com.example.mconomy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R.id.*
import com.example.mconomy.databinding.FragmentMicroBinding

class MicroFragment : Fragment() {


    private  lateinit var binding : FragmentMicroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMicroBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inventar.setOnClickListener{
            findNavController().navigate(action_microFragment_to_inventarMainPageFragment)
        }


        binding.backtomainMicro.setOnClickListener{
            findNavController().navigate(action_microFragment_to_firstInFragment)
        }

        binding.rentID.setOnClickListener{
            findNavController().navigate(action_microFragment_to_rentabilitateFragment)
        }

        binding.infoMicro.setOnClickListener {
            findNavController().navigate(action_microFragment_to_infoMicroFragment)
        }

        binding.toCostOportunitate.setOnClickListener {
            findNavController().navigate(action_microFragment_to_costDeOportunitateFragment)
        }

        val topBarText = activity?.findViewById<TextView>(topbartext)
        topBarText?.text = "Microeconomie"
    }
    }