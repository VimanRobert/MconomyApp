package com.example.mconomy.macrodir

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R

class RentabilitateAdapter : RecyclerView.Adapter<RentabilitateAdapter.RentabilitateViewHolder>() {
    private val rentList = ArrayList<RentabilitateData>()

    class RentabilitateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rentabilitateFinanciara: TextView = itemView.findViewById(R.id.rent_financiara_text)
        val rentabilitateaActiunilor: TextView = itemView.findViewById(R.id.rent_actiuni_text)
        val rataDobanzii: TextView = itemView.findViewById(R.id.rata_dobanzii_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentabilitateViewHolder {
        val itemInv = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_statistica_rentabilitate, parent, false)
        return RentabilitateViewHolder(itemInv)
    }

    override fun onBindViewHolder(holder: RentabilitateViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}