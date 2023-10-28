package com.devWithMuzammil.bilalmedicose.Adapters

import android.content.Context
import android.content.Intent
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.ui.ActivityMedicineDetails
import com.google.gson.Gson

class AdapterMedicine(private val context: Context,private var list: List<MedicineModel>) : RecyclerView.Adapter<AdapterMedicine.ViewHolder>() {
    fun updateList(newList: List<MedicineModel>) {
        list = newList
        notifyDataSetChanged()
    }

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
        holder.medName.text=medicines.medicineName
        holder.medFromula.text=medicines.formula
        if (medicines.strength?.isNotEmpty() == true) {
            holder.medPower.text = medicines.strength
        }
        else if (medicines.volume?.isNotEmpty() == true){
            holder.medPower.text = medicines.volume
        }
        holder.date.text=medicines.purchaseDate

        holder.layout.setOnClickListener {
            val selected = mutableListOf<MedicineModel>()
            selected.add(medicines)

            val gson = Gson()
            val Json = gson.toJson(selected)
            val intent = Intent(context, ActivityMedicineDetails::class.java)
            intent.putExtra("medicine", Json)
            //intent.putExtra("Section",studentModel.CurrentClass)
            context.startActivity(intent)
        }
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val medName:TextView=ItemView.findViewById(R.id.medName)
        val medPower:TextView=ItemView.findViewById(R.id.medPower)
        val medFromula:TextView=ItemView.findViewById(R.id.medFromula)
        val date:TextView=ItemView.findViewById(R.id.date)
        val layout:RelativeLayout=ItemView.findViewById(R.id.medLayout)
    }
}