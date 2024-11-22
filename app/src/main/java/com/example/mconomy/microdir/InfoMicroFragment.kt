package com.example.mconomy.microdir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mconomy.databinding.FragmentInfoMicroBinding

class InfoMicroFragment : Fragment() {
    private lateinit var binding: FragmentInfoMicroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoMicroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
