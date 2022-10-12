package com.example.mconomy.microdir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isNotEmpty
//import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentInventarSessionItemsBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//import com.google.firebase.database.DatabaseReference


class InventarSessionItemsFragment : Fragment() {

    private lateinit var viewModel: SessionViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SessionAdapter
    private lateinit var binding: FragmentInventarSessionItemsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventarSessionItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        val nrSesiune = view.findViewById<TextView>(R.id.nr_sesiune_input)
        val suma = view.findViewById<TextView>(R.id.nr_suma_totala_input)
        suma.text = Firebase.database.getReference("rez").toString()

         */


        /*
        val sterge_sesiune = view.findViewById<Button>(R.id.buton_sterge_sesiunea)
        sterge_sesiune.setOnClickListener {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1).setTitle("Atentie")
                    .setMessage("Doriti sa stergeti sesiunea respectiva ?")
                    .setNegativeButton("Nu") { dialog, which ->
                        return@setNegativeButton
                    }
                    .setPositiveButton("Da") { dialog, which -> return@setPositiveButton }
            }
        }
         */

        recyclerView = view.findViewById(R.id.session_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter = SessionAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this)[SessionViewModel::class.java]
        viewModel.listInv2.observe(viewLifecycleOwner) {
              adapter.updateSessionList(it)

            val sterge_sesiune = view.findViewById<Button>(R.id.buton_sterge_sesiunea)
            val sesiuni = view.findViewById<CardView>(R.id.sesiuni)
/*            sterge_sesiune.setOnClickListener {
                findNavController().navigate(R.id.action_inventarSessionItemsFragment_to_itemInventarFragment)
            }

 */
        }
    }
}