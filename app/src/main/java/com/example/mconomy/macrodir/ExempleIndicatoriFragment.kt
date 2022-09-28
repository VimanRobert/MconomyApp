package com.example.mconomy.macrodir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentExempleIndicatoriBinding


private lateinit var binding: FragmentExempleIndicatoriBinding
class ExempleIndicatoriFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExempleIndicatoriBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}