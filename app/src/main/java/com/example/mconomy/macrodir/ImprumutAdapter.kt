package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R

class ImprumutAdapter : RecyclerView.Adapter<ImprumutAdapter.ImprumutViewHolder>() {
    private val rambursList = ArrayList<ImprumutData>()

    class ImprumutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nrPerioada: TextView = itemView.findViewById(R.id.tip_perioada_row)
        val indexRata: TextView = itemView.findViewById(R.id.rata_row)
        val sumaRambursata: TextView = itemView.findViewById(R.id.suma_row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImprumutViewHolder {
        val itemInv = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_imprumuturi_item, parent, false)
        return ImprumutViewHolder(itemInv)
    }

    override fun onBindViewHolder(holder: ImprumutViewHolder, position: Int) {

        val currentItem = rambursList[position]

        holder.apply {
            nrPerioada.text = currentItem.perioada.toString()
            indexRata.text = currentItem.rata.toString()
            sumaRambursata.text = currentItem.suma.toString()
        }
    }

    override fun getItemCount(): Int {
        return rambursList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRambursList(invList: List<ImprumutData>) {
        this.rambursList.clear()
        this.rambursList.addAll(invList)
        notifyDataSetChanged()
    }
}
