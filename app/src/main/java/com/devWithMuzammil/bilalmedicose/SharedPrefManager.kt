package com.devWithMuzammil.bilalmedicose

import android.content.Context
import android.content.SharedPreferences
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.PurchaserModel
import com.google.gson.Gson
import java.lang.reflect.Type


class SharedPrefManager(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun putMedicineList(list:List<MedicineModel>): Boolean {
        editor.putString("ListMedicine", Gson().toJson(list))
        editor.commit()
        return true
    }
    fun putCosmeticsList(list:List<MedicineModel>): Boolean {
        editor.putString("ListCosmetics", Gson().toJson(list))
        editor.commit()
        return true
    }
    fun putGeneralList(list:List<MedicineModel>): Boolean {
        editor.putString("ListGeneral", Gson().toJson(list))
        editor.commit()
        return true
    }
    fun putHerbalList(list:List<MedicineModel>): Boolean {
        editor.putString("ListHerbal", Gson().toJson(list))
        editor.commit()
        return true
    }
    fun getHerbalList(): List<MedicineModel>{

        val json = sharedPref.getString("ListHerbal", "") ?: ""
        val type: Type = object : com.google.gson.reflect.TypeToken<List<MedicineModel?>?>() {}.getType()

        return if (!json.isNullOrEmpty()) {
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }
    fun putPurchaserList(list:List<PurchaserModel>): Boolean {
        editor.putString("ListPurchaser", Gson().toJson(list))
        editor.commit()
        return true
    }
    fun getPurchaserList(): List<PurchaserModel>{

        val json = sharedPref.getString("ListPurchaser", "") ?: ""
        val type: Type = object : com.google.gson.reflect.TypeToken<List<PurchaserModel?>?>() {}.getType()

        return if (!json.isNullOrEmpty()) {
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }

    fun getGeneralList(): List<MedicineModel>{

        val json = sharedPref.getString("ListGeneral", "") ?: ""
        val type: Type = object : com.google.gson.reflect.TypeToken<List<MedicineModel?>?>() {}.getType()

        return if (!json.isNullOrEmpty()) {
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }
    fun getCosmeticsList(): List<MedicineModel>{

        val json = sharedPref.getString("ListCosmetics", "") ?: ""
        val type: Type = object : com.google.gson.reflect.TypeToken<List<MedicineModel?>?>() {}.getType()

        return if (!json.isNullOrEmpty()) {
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }
    fun getMedicineList(): List<MedicineModel>{

        val json = sharedPref.getString("ListMedicine", "") ?: ""
        val type: Type = object : com.google.gson.reflect.TypeToken<List<MedicineModel?>?>() {}.getType()

        return if (!json.isNullOrEmpty()) {
            Gson().fromJson(json, type) ?: emptyList()
        } else {
            emptyList()
        }
    }
}