package com.devWithMuzammil.bilalmedicose.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.ProductViewModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.SharedPrefManager
import com.devWithMuzammil.bilalmedicose.databinding.ActivityMedicineDetailsBinding
import com.google.common.reflect.TypeToken
import com.google.firebase.Timestamp
import com.google.gson.Gson

class ActivityMedicineDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMedicineDetailsBinding
    private lateinit var mContext: Context
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext=this@ActivityMedicineDetails

        sharedPrefManager=SharedPrefManager(mContext)

        val medicine: List<MedicineModel> = Gson().fromJson(
            intent.getStringExtra("medicine"),
            object : TypeToken<List<MedicineModel?>?>() {}.getType()
        )
        var retailerId:String=""
        for (med in medicine){
            binding.medName.text=med.medicineName
            binding.medFormula.text=med.formula
            if(med.strength!!.isNotEmpty() && med.volume!!.isEmpty()) binding.tvMedPower.text=med.strength
            if(med.strength!!.isEmpty() && med.volume!!.isNotEmpty()) binding.tvMedPower.text=med.volume
            binding.retail.text=med.retail
            binding.price.text=med.price
            binding.bonus.text=med.bonus
            binding.discount.text=med.discount
            binding.purchaseDate.text=med.purchaseDate
            binding.totalQuantity.text=med.totalQuantity
            retailerId= med.purchasedFrom.toString()
        }
        val purchasers=productViewModel.getPurchaserByID(retailerId)
        var phoneNum=""
        for (purchaser in purchasers){
            binding.apply {
                purchsedFrom.text= purchaser.dealerName ?: ""
                purchsedFromPhone.text= purchaser.phoneName ?:""
                purchsedFromAddress.text= purchaser.dealerAddress ?:""
            }
            phoneNum= purchaser.phoneName.toString()
        }

        //Toast.makeText(mContext, ""+phoneNum, Toast.LENGTH_SHORT).show()
        binding.btnCall.setOnClickListener {
            if (phoneNum.isNotEmpty()) openDialer(phoneNum)
            else Toast.makeText(mContext, "Number is not saved", Toast.LENGTH_SHORT).show()
        }

        binding.btnUpdateMedicine.setOnClickListener {
            if (medicine.isNotEmpty()) {
                val singleMedicine = medicine[0] // Get the first (and only) MedicineModel from the list
                dialogBox(singleMedicine)
            } else {
                Toast.makeText(mContext, "Some thing went wrong ", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imgDelete.setOnClickListener {
            Toast.makeText(mContext, "Available soon", Toast.LENGTH_SHORT).show()
        }
    }
    private fun openDialer(phoneNumber: String) {
        val uri = Uri.parse("tel:$phoneNumber")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun dialogBox(medicine: MedicineModel) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_update_medicine, null)
        builder.setView(dialogView)

        val medName = dialogView.findViewById<EditText>(R.id.medName)
        val medFormula = dialogView.findViewById<EditText>(R.id.medFormula)
        val price = dialogView.findViewById<EditText>(R.id.price)
        val retail = dialogView.findViewById<EditText>(R.id.retail)
        val bonus = dialogView.findViewById<EditText>(R.id.bonus)
        val discount = dialogView.findViewById<EditText>(R.id.discount)
        val strength = dialogView.findViewById<EditText>(R.id.strength)
        val totalQuantity = dialogView.findViewById<EditText>(R.id.totalQuantity)
        val ml = dialogView.findViewById<EditText>(R.id.ml)

        // Set the text of each EditText field from the 'medicine' parameter
        medName.setText(medicine.medicineName)
        medFormula.setText(medicine.formula)
        price.setText(medicine.price)
        retail.setText(medicine.retail)
        bonus.setText(medicine.bonus)
        discount.setText(medicine.discount)

        // Check if 'strength' or 'volume' should be set based on your conditions
        if (medicine.strength!!.isNotEmpty() && medicine.volume!!.isEmpty()) {
            strength.setText(medicine.strength)
        } else if (medicine.strength!!.isEmpty() && medicine.volume!!.isNotEmpty()) {
            ml.setText(medicine.volume)
        }

        totalQuantity.setText(medicine.totalQuantity)

        builder.setPositiveButton("Save") { dialog, _ ->
            // Get the updated values from the EditText fields if needed
            val updatedMedicine = MedicineModel(
                medName.text.toString(),
                retail.text.toString(),
                price.text.toString(),
                discount.text.toString(),
                bonus.text.toString(),
                medicine.id,
                medicine.purchaseDate, // You may need to keep the original purchase date
                medicine.type,
                strength.text.toString(),
                ml.text.toString(),
                totalQuantity.text.toString(),
                medicine.purchasedFrom, // You may need to keep the original purchasedFrom value
                "",
                medFormula.text.toString(),

            )
             if (medicine.type=="Cosmetics"){
                 productViewModel.updateCosmetics(updatedMedicine)
                 productViewModel.getCosmetics().addOnCompleteListener { task ->
                     if (task.isSuccessful){
                         sharedPrefManager.putCosmeticsList(task.result.map { it.toObject(MedicineModel::class.java) })
                     }
                 }
             }
             else if (medicine.type=="General"){
                 productViewModel.updateGeneral(updatedMedicine)
                 productViewModel.getGeneral().addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         sharedPrefManager.putGeneralList(task.result.map { it.toObject(MedicineModel::class.java) })
                     }
                 }
             }
             else if (medicine.type=="Medicine"){
                 productViewModel.updateMedicine(updatedMedicine)
                 productViewModel.getMedicine().addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         sharedPrefManager.putMedicineList(task.result.map { it.toObject(MedicineModel::class.java) })
                     }
                 }
             }
             else if (medicine.type=="Herbal"){
                 productViewModel.updateHerbal(updatedMedicine)
                 productViewModel.getHerbal().addOnCompleteListener { task ->
                     if (task.isSuccessful) {
                         sharedPrefManager.putHerbalList(task.result.map { it.toObject(MedicineModel::class.java) })
                     }
                 }
             }
             else Toast.makeText(mContext, "Some thing went wrong", Toast.LENGTH_SHORT).show()

            // Perform any necessary update operations with 'updatedMedicine'

            // Dismiss the dialog
            dialog.dismiss()
            startActivity(Intent(mContext,MainActivity::class.java))
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }


}