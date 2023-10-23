package com.devWithMuzammil.bilalmedicose.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devWithMuzammil.bilalmedicose.Adapters.AdapterMedicine
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.ProductViewModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.SharedPrefManager
import com.devWithMuzammil.bilalmedicose.Utils
import kotlinx.coroutines.launch

class CosmaticFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterMedicine
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var utils: Utils
    private var list: List<MedicineModel> = listOf()
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cosmatic, container, false)

        mContext=requireContext()

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.rvCosmatics)
        recyclerView.layoutManager = LinearLayoutManager(context) // You can use GridLayoutManager or other layout managers if needed
        adapter = AdapterMedicine(mContext,list) // Initialize your adapter here
        recyclerView.adapter = adapter


        utils = Utils(mContext)

        sharedPrefManager= SharedPrefManager(mContext)

        getData()

        return view
    }
    private fun getData(){
        lifecycleScope.launch {
            list=sharedPrefManager.getCosmeticsList()
            //Toast.makeText(mContext, ""+list.size, Toast.LENGTH_SHORT).show()
            adapter.updateList(list)
        }
    }

}