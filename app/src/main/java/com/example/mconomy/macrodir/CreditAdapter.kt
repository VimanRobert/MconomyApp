package com.example.mconomy.macrodir

import android.annotation.SuppressLint
import android.content.Context
import android.database.DataSetObserver
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import android.widget.TextView
import com.example.mconomy.R

class CreditAdapter(private val context: Context, private val arrayList: ArrayList<CreditData>):
    Adapter, ListAdapter {

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.credite_item_list, null)

        val tipImprumut : TextView = view.findViewById(R.id.titlu_tip_credit)

        tipImprumut.text = arrayList[position].tipImprumut

        return view
    }


    override fun registerDataSetObserver(observer: DataSetObserver?) {
        observer?.onChanged().apply {
            Log.i("change event", "list of credit types")
        }
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        observer.apply {
            Log.i("change event", "exit list")
        }
    }

    override fun getCount(): Int = arrayList.size

    override fun getItem(position: Int): Any {
        Log.i("credit item", "credit type: $position")
        return position
    }

    override fun getItemId(position: Int): Long {
        arrayList[position]
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getItemViewType(position: Int): Int {
        return position.and(arrayList.size)
    }

    override fun getViewTypeCount(): Int = arrayList.count()

    override fun isEmpty(): Boolean {
        return false
    }

    override fun areAllItemsEnabled(): Boolean {
        return arrayList.isNotEmpty()
    }

    override fun isEnabled(position: Int): Boolean {
        return true
    }
}