package com.example.mconomy.macrodir

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mconomy.R
import com.example.mconomy.databinding.CrediteListBinding

class CrediteFragment : Fragment() {
    private lateinit var binding: CrediteListBinding
    private lateinit var arrayList: ArrayList<CreditData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CrediteListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList = ArrayList()

        val tipCreditArray = arrayOf(
            getString(R.string.ipotecar),
            getString(R.string.imobiliar),
            getString(R.string.pe_viata),
            getString(R.string.obligatar),
            getString(R.string.de_consum),
            getString(R.string.bancar),
            getString(R.string.comercial),
            getString(R.string.nebancar)
        )
        val rataDobanziiArray = arrayOf(5.0, 7.0, 10.0, 20.0, 30.0, 35.0, 1.0, 8.5, 5.5, 6.0, 6.5)
        val perioadaImprumutArray =
            arrayOf(
                getString(R.string.max_10_ani),
                getString(R.string.max_15_ani),
                getString(R.string.max_20_ani),
                getString(R.string.max_25_ani),
                getString(R.string.max_30_ani),
                getString(R.string.max_35_ani),
                getString(R.string.pe_toata_viata)
            )
        val descriereArray = arrayOf(
            getString(R.string.credit_ipotecar),
            getString(R.string.credit_imobiliar),
            getString(R.string.credit_pe_viata),
            getString(R.string.credit_obligatar),
            getString(R.string.credit_de_consum),
            getString(R.string.credit_bancar),
            getString(R.string.credit_comercial),
            getString(R.string.credit_nebancar)
        )

        val creditIpotecar = CreditData(
            tipCreditArray[0],
            rataDobanziiArray[3],
            perioadaImprumutArray[2],
            descriereArray[0]
        )
        val creditImobiliar = CreditData(
            tipCreditArray[1],
            rataDobanziiArray[5],
            perioadaImprumutArray[2],
            descriereArray[1]
        )
        val creditPeViata = CreditData(
            tipCreditArray[2],
            rataDobanziiArray[6],
            perioadaImprumutArray[4],
            descriereArray[2]
        )
        val creditObligatar = CreditData(
            tipCreditArray[3],
            rataDobanziiArray[1],
            perioadaImprumutArray[1],
            descriereArray[3]
        )
        val creditConsum = CreditData(
            tipCreditArray[4],
            rataDobanziiArray[7],
            perioadaImprumutArray[2],
            descriereArray[4]
        )
        val creditBancar = CreditData(
            tipCreditArray[5],
            rataDobanziiArray[1],
            perioadaImprumutArray[1],
            descriereArray[5]
        )
        val creditComercial = CreditData(
            tipCreditArray[6],
            rataDobanziiArray[6],
            perioadaImprumutArray[1],
            descriereArray[6]
        )
        val creditNebancar = CreditData(
            tipCreditArray[7],
            rataDobanziiArray[3],
            perioadaImprumutArray[3],
            descriereArray[7]
        )
        Log.i("x", "rata dobanzii length ${rataDobanziiArray.size}")
        Log.i("x", "rata dobanzii index ${rataDobanziiArray[6]}")
        Log.i("y", "perioada imprumut length ${perioadaImprumutArray.size}")
        Log.i("y", "perioada imprumut index ${perioadaImprumutArray[1]}")

        arrayList.apply {
            add(creditIpotecar)
            add(creditImobiliar)
            add(creditPeViata)
            add(creditObligatar)
            add(creditConsum)
            add(creditBancar)
            add(creditComercial)
            add(creditNebancar)
        }

        binding.apply {
            crediteListView.isClickable = true
            crediteListView.adapter = CreditAdapter(requireContext(), arrayList)
            crediteListView.setOnItemClickListener { _, _, position, _ ->
                val tip = tipCreditArray[position]
                val rata = rataDobanziiArray[position]
                val perioada = perioadaImprumutArray[position]
                val descriere = descriereArray[position]

                val bundle = Bundle()
                bundle.putString("tip", tip)
                bundle.putDouble("rata", rata)
                bundle.putString("perioada", perioada)
                bundle.putString("descriere", descriere)

                findNavController().navigate(R.id.action_crediteFragment_to_creditDetail, bundle)
            }
        }
    }
}
