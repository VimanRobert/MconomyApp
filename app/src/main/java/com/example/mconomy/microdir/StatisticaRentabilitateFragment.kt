package com.example.mconomy.microdir

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentStatisticaRentabilitateBinding
import com.example.mconomy.macrodir.RentabilitateData
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class StatisticaRentabilitateFragment : Fragment() {


    private lateinit var binding: FragmentStatisticaRentabilitateBinding
    private lateinit var pieChart: PieChart
    private lateinit var setPieChart: PieDataSet
    private lateinit var pieData: PieData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticaRentabilitateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val rambursList = mutableListOf<PieEntry>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*
        val profitNet = view.findViewById<EditText>(R.id.profit_net_input)
        val capitalPropriu = view.findViewById<EditText>(R.id.capitaluri_input)
        val activeTotale = view.findViewById<EditText>(R.id.active_totale_input)
        val dobanda = view.findViewById<EditText>(R.id.dobanda_input)
        val datorii = view.findViewById<EditText>(R.id.datorii_input)

 */

        //val rambursList = mutableListOf<PieEntry>()

        pieChart = binding.pieChartRentabilitate
        getPieData()
        setPieChart = PieDataSet(rambursList, "Pragul de rentabilitate")
        pieData = PieData(setPieChart)
        pieChart.data = pieData
        setPieChart.setColors(Color.GREEN, Color.RED, Color.YELLOW)
        setPieChart.valueTextColor = Color.BLUE
        setPieChart.valueTextSize = 24f
        pieChart.description.isEnabled = true

        //val list = ArrayList<RentabilitateData>()
        /*
        val data = RentabilitateData(
            rentabilitateFinanciara = rentabilitateFin.text.toString().toDouble(),
            rentabilitateaActiunilor = rentabilitateaAct.text.toString().toDouble(),
            rataDobanzii = rataDob.text.toString().toDouble()
        )

         */
        //list.add(RentabilitateData(rentabilitateFinanciara, rentabilitateaActiunilor, rataDobanzii))
        //val list2 = ArrayList<PieEntry>()

    }

    private fun getPieData(){
        val rentFin = view?.findViewById<TextView>(R.id.rent_financiara_text)
        val rentAct = view?.findViewById<TextView>(R.id.rent_actiuni_text)
        val rataD = view?.findViewById<TextView>(R.id.rata_dobanzii_text)
        val data = RentabilitateData(
            rentabilitateFinanciara = rentFin.toString().toFloat(),
            rentabilitateaActiunilor = rentAct.toString().toFloat(),
            rataDobanzii =rataD.toString().toFloat()
        )
        rambursList.add(PieEntry(data.rentabilitateFinanciara, "Rent. Financiara", data.rentabilitateFinanciara))
        rambursList.add(PieEntry(data.rentabilitateaActiunilor, "Rent. Actiuni", data.rentabilitateaActiunilor))
        rambursList.add(PieEntry(data.rataDobanzii, "Rata Dobanzii", data.rataDobanzii))
    }

}