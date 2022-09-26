package com.example.mconomy.microdir

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mconomy.R
import com.example.mconomy.databinding.FragmentInventarBinding
import com.example.mconomy.databinding.FragmentItemListBinding
import com.example.mconomy.microdir.placeholder.PlaceholderContent


class ItemInventarFragment : Fragment() {

    private lateinit var binding : FragmentItemListBinding
    private lateinit var viewModel: InventarViewModel
    private lateinit var recyclerView: RecyclerView
    lateinit var adapter: InventarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.list_inventar)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter = InventarAdapter()
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this).get(viewModel::class.java)
        viewModel.listInv2.observe(viewLifecycleOwner, Observer {
            adapter.updateInventarList(it)
        })
    }
}