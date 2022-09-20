package com.example.mconomy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R.id.*
import com.example.mconomy.databinding.FragmentMicroBinding

class MicroFragment : Fragment() {


    private  lateinit var binding : FragmentMicroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMicroBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_micro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inventar = view.findViewById<Button>(R.id.inventar)
        inventar.setOnClickListener{
            findNavController().navigate(action_microFragment_to_blankFragment)
        }

        val backtomainMicro = view.findViewById<Button>(R.id.backtomainMicro)
        backtomainMicro.setOnClickListener{
            findNavController().navigate(action_microFragment_to_firstInFragment)
        }
        val rentID = view.findViewById<Button>(R.id.rentID)
        rentID.setOnClickListener{
            findNavController().navigate(action_microFragment_to_rentabilitateFragment)
        }
    }
    }