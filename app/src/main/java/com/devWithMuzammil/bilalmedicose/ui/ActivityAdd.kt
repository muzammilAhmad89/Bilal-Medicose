package com.devWithMuzammil.bilalmedicose.ui

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.ProductViewModel
import com.devWithMuzammil.bilalmedicose.Models.PurchaserModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.databinding.ActivityAddBinding
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ActivityAdd : AppCompatActivity() {
    private var purchaserSelected: String?=null
    private lateinit var binding: ActivityAddBinding
    private var selectedProductType: String? = null
    private val productViewModel:ProductViewModel by viewModels()
    private lateinit var mContext: Context
    private lateinit var purchaserList: ArrayList<PurchaserModel>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext=this@ActivityAdd
        purchaserList= ArrayList()
        //Toast.makeText(mContext, ""+getCurrentDate(), Toast.LENGTH_SHORT).show()

        ///////////////spinner setting/////////////
        val items = listOf("Herbal", "Medicine", "General", "Cosmetics")

        val spinner = findViewById<Spinner>(R.id.spinnerType)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedProductType = items[position]
                // You can now use the selectedProductType as needed
                // For example, you can display it in a TextView or perform some action.
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected (if necessary)
            }
        }

        binding.addPurchaser.setOnClickListener {

            try {
                if (binding.purchaserName.text.isNotEmpty()){
                    val purchaserModel=PurchaserModel(
                        binding.purchaserName.text.toString(),
                        binding.purchaserPhone.text.toString(),
                        binding.purchseeAddress.text.toString()
                    )
                    productViewModel.savePurchaser(purchaserModel)
                    Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show()
                }
                else binding.purchaserName.error = "enter a name"

            }
            catch (e:Exception){
                Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
            }


        }
        binding.btnSave.setOnClickListener {
            if (binding.medName.text.isNotEmpty()||binding.price.text.isNotEmpty()){
                if (selectedProductType=="Cosmetics") uploadCosmetics()
                    else if (selectedProductType=="General") uploadGeneral()
                        else if (selectedProductType=="Medicine") uploadMedicine()
                            else if (selectedProductType=="Herbal") uploadHerbalMedicine()
                                else Toast.makeText(mContext, "Some thing went wrong", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(mContext, "enter valid name or price", Toast.LENGTH_SHORT).show()
            }
        }
        fetchAndInitializeSpinner()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadHerbalMedicine() {
        try {
            val medicineModel=MedicineModel(
                binding.medName.text.toString(),
                binding.retail.text.toString(),
                binding.discount.text.toString(),
                binding.bonus.text.toString(),
                "",
                getCurrentDate(),
                selectedProductType,
                binding.strength.text.toString(),
                binding.ml.text.toString(),
                binding.totalQuantity.text.toString(),
                purchaserSelected,
                "",
                binding.medFormula.text.toString()
            )
            productViewModel.uploadHerbalMedicine(medicineModel)
            Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show()

        }
        catch (e:Exception){
            Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadMedicine() {
        try {
            val medicineModel=MedicineModel(
                binding.medName.text.toString(),
                binding.retail.text.toString(),
                binding.discount.text.toString(),
                binding.bonus.text.toString(),
                "",
                getCurrentDate(),
                selectedProductType,
                binding.strength.text.toString(),
                binding.ml.text.toString(),
                binding.totalQuantity.text.toString(),
                purchaserSelected,
                "",
                binding.medFormula.text.toString()
            )
            productViewModel.uploadMedicine(medicineModel)
            Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show()

        }
        catch (e:Exception){
            Toast.makeText(mContext, "this:"+e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadGeneral() {
        try {
            val medicineModel=MedicineModel(
                binding.medName.text.toString(),
                binding.retail.text.toString(),
                binding.discount.text.toString(),
                binding.bonus.text.toString(),
                "",
                getCurrentDate(),
                selectedProductType,
                binding.strength.text.toString(),
                binding.ml.text.toString(),
                binding.totalQuantity.text.toString(),
                purchaserSelected,
                "",
                binding.medFormula.text.toString()
            )
            productViewModel.uploadGeneral(medicineModel)
            Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show()

        }
        catch (e:Exception){
            Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadCosmetics() {
        try {
            val medicineModel=MedicineModel(
                binding.medName.text.toString(),
                binding.retail.text.toString(),
                binding.discount.text.toString(),
                binding.bonus.text.toString(),
                "",
                getCurrentDate(),
                selectedProductType,
                binding.strength.text.toString(),
                binding.ml.text.toString(),
                binding.totalQuantity.text.toString(),
                purchaserSelected,
                "",
                binding.medFormula.text.toString()
            )
            productViewModel.uploadCosmetics(medicineModel)
            Toast.makeText(mContext, "Added Successfully", Toast.LENGTH_SHORT).show()

        }
        catch (e:Exception){
            Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchAndInitializeSpinner() {

        lifecycleScope.launch {
            productViewModel.getPurchaser().addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val document=task.result
                    for (document in document){
                        val purchaser=document.toObject(PurchaserModel::class.java)
                        purchaserList.add(purchaser)
                    }
                    //Toast.makeText(mContext, ""+purchaserList.size, Toast.LENGTH_SHORT).show()
                    // Set up the Spinner
                    val distinctDealerNames = purchaserList.map { it.dealerName }.distinct() // Get distinct dealer names
                    //Toast.makeText(mContext, "" + distinctDealerNames.size, Toast.LENGTH_SHORT).show()
                    val adapter = ArrayAdapter(mContext, android.R.layout.simple_spinner_item, distinctDealerNames)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerPurchasedFrom.adapter = adapter

                    // Select the first item in the spinner by default (assuming purchaserList is not empty)
                    if (distinctDealerNames.isNotEmpty()) {
                        binding.spinnerPurchasedFrom.setSelection(0)
                    }


                    // Add an item selected listener to toast the selected value
                    binding.spinnerPurchasedFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val purchaser= purchaserList[position]
                            purchaserSelected=purchaser.Id
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Handle the case when nothing is selected (optional)
                        }
                    }
                }

            }
                .addOnFailureListener {e:Exception->
                    Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
                }
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        return currentDate.format(formatter)
    }
}