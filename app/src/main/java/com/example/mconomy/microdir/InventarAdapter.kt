package com.example.mconomy.microdir

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R

class InventarAdapter(private val invList: ArrayList<InventarData>): RecyclerView.Adapter<InventarAdapter.InventarViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventarViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: InventarViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return invList.size
    }


    class InventarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val produs: TextView = itemView.findViewById(R.id.numeProdusID)
        val pret: TextView = itemView.findViewById(R.id.pretID)
        val cantitate: TextView = itemView.findViewById(R.id.cantitateID)
        var rez: TextView = itemView.findViewById(R.id.rezInitial)
        var rezTotal: TextView = itemView.findViewById(R.id.rezTotal)

    }
}