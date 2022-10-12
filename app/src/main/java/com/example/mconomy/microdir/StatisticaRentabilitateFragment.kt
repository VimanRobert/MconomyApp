package com.example.mconomy.microdir

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.green
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.example.mconomy.databinding.FragmentStatisticaRentabilitateBinding
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


        pieChart = binding.pieChartRentabilitate
        getPieData()
        setPieChart = PieDataSet(rambursList, "Pragul de rentabilitate")
        pieData = PieData(setPieChart)
        pieChart.data = pieData
        setPieChart.setColors(Color.GREEN, Color.RED, Color.CYAN)
        setPieChart.valueTextColor = Color.BLACK
        setPieChart.valueTextSize = 24f
        pieChart.description.isEnabled = true

    }

    private fun getPieData(){

        val args = this.arguments
        val text1 = args?.getFloat("keyRentFin")
        val text2 = args?.getFloat("keyRentAct")
        val text3 = args?.getFloat("keyRataDob")



        if (text1 != null && text2 != null && text3 != null) {
            rambursList.add(PieEntry(text1, "Rent. Financiara", text1))
            rambursList.add(PieEntry(text2, "Rent. Actiuni", text2))
            rambursList.add(PieEntry(text3, "Rata Dobanzii", text3))
            }else{
                Toast.makeText(context, "A aparut o eroare !", Toast.LENGTH_SHORT).show()
        }
    }

}