package com.example.mconomy.microdir

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R

class InventarAdapter : RecyclerView.Adapter<InventarAdapter.InventarViewHolder>() {

    private val invList = ArrayList<InventarData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventarViewHolder {
        val itemInv =
            LayoutInflater.from(parent.context).inflate(R.layout.inventar_list_item, parent, false)
        return InventarViewHolder(itemInv)
    }

    override fun onBindViewHolder(holder: InventarViewHolder, position: Int) {
        val currentItem = invList[position]

        holder.produs.text = currentItem.produs
        holder.cantitate.text = currentItem.cantitate.toString()
        holder.pret.text = currentItem.pret.toString()
        holder.rez.text = currentItem.rez.toString()
        holder.rezTotal.text = currentItem.rezTotal.toString()
    }

    override fun getItemCount(): Int {
        return invList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateInventarList(invList: List<InventarData>) {
        this.invList.clear()
        this.invList.addAll(invList)
        notifyDataSetChanged()
    }


    class InventarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val produs: TextView = itemView.findViewById(R.id.nume_produs_input)
        val pret: TextView = itemView.findViewById(R.id.pret_input)
        val cantitate: TextView = itemView.findViewById(R.id.cantitate_input)
        var rez: TextView = itemView.findViewById(R.id.rezultat_input)
        var rezTotal: TextView = itemView.findViewById(R.id.rezultat_acumulat_input)

    }
}