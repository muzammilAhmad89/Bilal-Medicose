package com.devWithMuzammil.bilalmedicose.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.databinding.ActivityMedicineDetailsBinding
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class ActivityMedicineDetails : AppCompatActivity() {
    private lateinit var binding: ActivityMedicineDetailsBinding
    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext=this@ActivityMedicineDetails

        val medicine: List<MedicineModel> = Gson().fromJson(
            intent.getStringExtra("medicine"),
            object : TypeToken<List<MedicineModel?>?>() {}.getType()

        )
    }
}