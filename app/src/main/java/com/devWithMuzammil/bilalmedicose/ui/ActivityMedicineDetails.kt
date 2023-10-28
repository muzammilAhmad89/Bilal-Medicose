package com.devWithMuzammil.bilalmedicose.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.ProductViewModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.databinding.ActivityMedicineDetailsBinding
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ActivityMedicineDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMedicineDetailsBinding
    private lateinit var mContext: Context
    private val productViewModel: ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext=this@ActivityMedicineDetails

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
                purchsedFrom.text=purchaser.dealerName
                purchsedFromPhone.text=purchaser.phoneName
                purchsedFromAddress.text=purchaser.dealerAddress
            }
            phoneNum= purchaser.phoneName.toString()
        }

        //Toast.makeText(mContext, ""+phoneNum, Toast.LENGTH_SHORT).show()
        binding.btnCall.setOnClickListener {
            if (phoneNum.isNotEmpty()) openDialer(phoneNum)
            else Toast.makeText(mContext, "Number is not saved", Toast.LENGTH_SHORT).show()
        }
    }
    private fun openDialer(phoneNumber: String) {
        val uri = Uri.parse("tel:$phoneNumber")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        startActivity(intent)
    }
}