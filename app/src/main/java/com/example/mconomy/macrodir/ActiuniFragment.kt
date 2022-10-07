package com.example.mconomy.macrodir

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentActiuniBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*

class ActiuniFragment : Fragment() {

    private lateinit var binding: FragmentActiuniBinding
    private lateinit var barChart: BarChart
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    private lateinit var pieChart: PieChart
    private lateinit var pieDataSet: PieDataSet
    private lateinit var pieData: PieData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiuniBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val actiuniList = mutableListOf<BarEntry>()
    private val actiuniList2 = mutableListOf<PieEntry>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val actiuniList = mutableListOf<BarEntry>()

        barChart = view.findViewById(R.id.bar_chart_actiuni)
        getBarData()
        barDataSet = BarDataSet(actiuniList, "Portofoliul pe 4 zile")
        barData = BarData(barDataSet)
        barChart.data = barData
        //barDataSet.setColors(Color.RED)
        barDataSet.setColors(Color.RED, Color.BLUE, Color.GREEN)
        barDataSet.valueTextSize = 26f

        barChart.description.isEnabled = true

        pieChart = binding.pieChartActiuni
        getPieData()
        pieDataSet = PieDataSet(actiuniList2, "Diferenta de flux")
        pieData = PieData(pieDataSet)
        pieChart.data = pieData
        pieDataSet.setColors(Color.GREEN, Color.BLUE, Color.CYAN, Color.RED)
        pieDataSet.valueColors.add(Color.BLACK)
        pieDataSet.valueTextSize = 26f
    }

    private fun getBarData() {
        actiuniList.add(BarEntry(2f, 10f))
        actiuniList.add(BarEntry(5f, 20f))
        actiuniList.add(BarEntry(3f, 30f))
        actiuniList.add(BarEntry(4f, 40f))

    }

    private fun getPieData() {
        actiuniList2.add(PieEntry(2f, "Ziua 1", 200))
        actiuniList2.add(PieEntry(5f, "Ziua 2", 500))
        actiuniList2.add(PieEntry(4f, "Ziua 3", 400))
        actiuniList2.add(PieEntry(7f, "Ziua 4", 700))
        val pieStat = binding.pieShowStatus
        for (i in actiuniList2) {
            pieStat.append(i.label.toString() + ": " + i.data + "\n")
        }
    }
}