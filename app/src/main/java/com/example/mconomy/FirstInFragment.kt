package com.example.mconomy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mconomy.databinding.FragmentFirstInBinding

class FirstInFragment : Fragment() {

    private lateinit var binding: FragmentFirstInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstInBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toMicroeconomy.setOnClickListener{
            findNavController().navigate(R.id.action_firstInFragment_to_microFragment)
        }
        binding.toMacroeconomy.setOnClickListener{
            findNavController().navigate(R.id.action_firstInFragment_to_macroFragment)
        }
        val topBarText = activity?.findViewById<TextView>(R.id.topbartext)
        topBarText?.text = "Mconomy"
    }

}