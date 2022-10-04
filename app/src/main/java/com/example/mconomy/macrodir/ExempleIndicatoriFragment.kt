package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mconomy.databinding.FragmentExempleIndicatoriBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: FragmentExempleIndicatoriBinding

class ExempleIndicatoriFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExempleIndicatoriBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}