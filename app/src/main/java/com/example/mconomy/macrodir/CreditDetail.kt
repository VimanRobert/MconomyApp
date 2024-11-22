package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentCreditDetailBinding

class CreditDetail : Fragment() {

    private lateinit var binding: FragmentCreditDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = this.arguments
        val tip = args?.getString("tip")
        val rata = args?.getDouble("rata")
        val perioada = args?.getString("perioada")
        val descriere = args?.getString("descriere")

        binding.apply {
            tipCredit.text = tip.toString()
            rataDobanziiCredit.text = "${getString(R.string.rata_dobanzii)} ${rata.toString()}%"
            perioadaImprumutCredit.text =
                "${getString(R.string.perioada_imprumut)} ${perioada.toString()}"
            descriereTipCredit.text = descriere.toString()
        }
    }
}
