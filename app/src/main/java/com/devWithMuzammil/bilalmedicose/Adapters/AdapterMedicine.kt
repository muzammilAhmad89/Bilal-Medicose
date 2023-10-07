package com.devWithMuzammil.bilalmedicose.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.R

class AdapterMedicine(private val list: ArrayList<MedicineModel>) : RecyclerView.Adapter<AdapterMedicine.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_design_medicine, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val medicines=list[position]


    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }
}