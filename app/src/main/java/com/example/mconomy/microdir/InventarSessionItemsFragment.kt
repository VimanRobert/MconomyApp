package com.example.mconomy.microdir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentInventarSessionItemsBinding
//import com.google.firebase.database.DatabaseReference


class InventarSessionItemsFragment : Fragment() {

    private lateinit var viewModel: InventarViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SessionAdapter
    //private lateinit var databaseReference: DatabaseReference
    private lateinit var binding: FragmentInventarSessionItemsBinding
    //val args: InventarSessionItemsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventarSessionItemsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //val sumaTotala = view.findViewById<TextView>(R.id.rezultat_acumulat_input)


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
        viewModel = ViewModelProvider(this)[InventarViewModel::class.java]
        viewModel.listInv2.observe(viewLifecycleOwner) {
            //  adapter.updateInventarList(it)

            // 'it' face referinta la InventarData
        }
    }
}