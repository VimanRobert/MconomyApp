package com.example.mconomy.microdir

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R

class SessionAdapter : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    private val invList = ArrayList<SessionData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SessionViewHolder {
        val itemInv =
            LayoutInflater.from(parent.context).inflate(R.layout.inventar_sessions, parent, false)
        return SessionViewHolder(itemInv)

    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val currentItem = invList[position]

        holder.nrSesiune.text = currentItem.nrSesiuneInv.toString()
        holder.nrProduse.text = currentItem.nrProduseInv.toString()
        holder.nrSumaTotala.text = currentItem.sumaTotalaInv.toString()

    }

    override fun getItemCount() = invList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateInventarList(invList: List<SessionData>) {
        this.invList.clear()
        this.invList.addAll(invList)
        notifyDataSetChanged()
    }

    class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nrSesiune: TextView = itemView.findViewById(R.id.nr_sesiune_input)
        val nrProduse: TextView = itemView.findViewById(R.id.nr_produse_input)
        val nrSumaTotala: TextView = itemView.findViewById(R.id.nr_suma_totala_input)
    }
}