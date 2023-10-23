package com.devWithMuzammil.bilalmedicose.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devWithMuzammil.bilalmedicose.data.Repo
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

class ProductViewModel(context: Application) : AndroidViewModel(context) {

    private val repo = Repo(context)


    fun savePurchaser(purchaserModel: PurchaserModel){ repo.savePurchaser(purchaserModel) }
    fun getPurchaser():Task<QuerySnapshot>{ return repo.getPurchaser() }
    fun uploadMedicine(medicineModel: MedicineModel){ repo.uploadMedicine(medicineModel) }
    fun uploadCosmetics(medicineModel: MedicineModel){ repo.uploadCosmetics(medicineModel) }
    fun uploadHerbalMedicine(medicineModel: MedicineModel){ repo.uploadHerbalMedicine(medicineModel) }
    fun uploadGeneral(medicineModel: MedicineModel){ repo.uploadGeneral(medicineModel) }
    fun getMedicine(): Task<QuerySnapshot> { return repo.GetMedicine() }
    fun getHerbal(): Task<QuerySnapshot> { return repo.getHerbal() }
    fun getGeneral(): Task<QuerySnapshot> { return repo.getGeneral() }
    fun getCosmetics(): Task<QuerySnapshot> { return repo.getCosmetics() }

}