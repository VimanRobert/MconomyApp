package com.example.mconomy.macrodir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mconomy.databinding.FragmentRealTimeActiuniBinding
import com.squareup.picasso.Picasso


class RealTimeActiuniFragment : Fragment() {

    private lateinit var binding: FragmentRealTimeActiuniBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRealTimeActiuniBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teslaImg = binding.teslaImg
        val teslaUrl = "http://media.foxbusiness.com/BrightCove/854081161001/202009/3752/854081161001_6189119455001_6189125931001-vs.jpg"
        val teslaUrl2 = "https://s3-symbol-logo.tradingview.com/tesla--big.svg"
        Picasso.get().load(teslaUrl)
                     .into(teslaImg)
    }
}