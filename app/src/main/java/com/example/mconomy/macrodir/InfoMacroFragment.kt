package com.example.mconomy.macrodir


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mconomy.databinding.FragmentInfoMacroBinding


class InfoMacroFragment : Fragment() {

    private lateinit var binding: FragmentInfoMacroBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoMacroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}