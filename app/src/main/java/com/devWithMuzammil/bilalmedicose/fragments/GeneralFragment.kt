package com.devWithMuzammil.bilalmedicose.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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


class GeneralFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterMedicine
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var utils: Utils
    private var list: List<MedicineModel> = listOf()
    private lateinit var mContext: Context
    private lateinit var sharedPrefManager: SharedPrefManager
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_general, container, false)

        mContext=requireContext()

        // Initialize the RecyclerView
        recyclerView = view.findViewById(R.id.rvGeneral)
        recyclerView.layoutManager = LinearLayoutManager(context) // You can use GridLayoutManager or other layout managers if needed
        adapter = AdapterMedicine(mContext,list) // Initialize your adapter here
        recyclerView.adapter = adapter


        utils = Utils(mContext)

        sharedPrefManager= SharedPrefManager(mContext)

        getData()
        // Initialize the SearchView
        searchView = view.findViewById(R.id.svGeneral)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter the original list based on the search query
                val filteredList = list.filter { medicine ->
                    // Check if the search query matches any of the parameters
                    val searchQuery = newText.orEmpty().toLowerCase()
                    medicine.medicineName?.toLowerCase()?.contains(searchQuery) == true ||
                            medicine.retail?.toLowerCase()?.contains(searchQuery) == true ||
                            medicine.price?.toLowerCase()?.contains(searchQuery) == true ||
                            medicine.discount?.toLowerCase()?.contains(searchQuery) == true ||
                            medicine.discount?.toLowerCase()?.contains(searchQuery) == true
                    // Add similar checks for other parameters as needed
                    // Example: medicine.strength?.toLowerCase()?.contains(searchQuery) == true ||
                    // ...

                }

                // Update the adapter with the filtered list
                adapter.updateList(filteredList)

                return true
            }

        })


        return view
    }
    private fun getData(){
        lifecycleScope.launch {
            list=sharedPrefManager.getGeneralList()
            //Toast.makeText(mContext, ""+list, Toast.LENGTH_SHORT).show()
            adapter.updateList(list)
        }
    }

}