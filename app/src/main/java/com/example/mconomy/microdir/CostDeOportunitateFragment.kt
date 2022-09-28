package com.example.mconomy.microdir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentCostDeOportunitateBinding

class CostDeOportunitateFragment : Fragment() {

    private lateinit var binding: FragmentCostDeOportunitateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCostDeOportunitateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}