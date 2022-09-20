package com.example.mconomy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstInBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_first_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val micro = view.findViewById<Button>(R.id.toMicroeconomy)
        val macro = view.findViewById<Button>(R.id.toMacroeconomy)


        micro.setOnClickListener{
            findNavController().navigate(R.id.action_firstInFragment_to_microFragment)
        }
        macro.setOnClickListener{
            findNavController().navigate(R.id.action_firstInFragment_to_macroFragment)
        }
    }

}