package com.example.mconomy.microdir


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentInventarMainPageBinding


class InventarMainPageFragment : Fragment() {
    private lateinit var binding: FragmentInventarMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventarMainPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            inventarNou.setOnClickListener {
                findNavController().navigate(R.id.action_inventarMainPageFragment_to_blankFragment)
            }
            todatabase.setOnClickListener {
                findNavController().navigate(R.id.action_inventarMainPageFragment_to_inventarSessionItemsFragment)
            }
        }
    }
}
