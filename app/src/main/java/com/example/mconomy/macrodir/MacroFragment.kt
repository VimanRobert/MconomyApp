package com.example.mconomy.macrodir

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mconomy.MainActivity
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentMacroBinding

class MacroFragment : Fragment() {
    private lateinit var binding: FragmentMacroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMacroBinding.inflate(inflater, container ,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_macro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.backtomainMacro.setOnClickListener{
            findNavController().navigate(R.id.action_macroFragment_to_firstInFragment)
        }
    }
}