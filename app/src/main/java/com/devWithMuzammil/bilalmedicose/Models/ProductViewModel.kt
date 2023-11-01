package com.devWithMuzammil.bilalmedicose.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.devWithMuzammil.bilalmedicose.SharedPrefManager
import com.devWithMuzammil.bilalmedicose.data.Repo
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

class ProductViewModel(context: Application) : AndroidViewModel(context) {

    private val repo = Repo(context)
    private val sharedPrefManager=SharedPrefManager(context)


    fun savePurchaser(purchaserModel: PurchaserModel){ repo.savePurchaser(purchaserModel) }
    fun getPurchaser():Task<QuerySnapshot>{ return repo.getPurchaser() }
    fun getPurchaserByID(purchaserId:String): List<PurchaserModel> { return sharedPrefManager.getPurchaserList().filter { purchaser-> purchaser.Id.equals(purchaserId) }}
    fun uploadMedicine(medicineModel: MedicineModel){ repo.uploadMedicine(medicineModel) }
    fun uploadCosmetics(medicineModel: MedicineModel){ repo.uploadCosmetics(medicineModel) }
    fun uploadHerbalMedicine(medicineModel: MedicineModel){ repo.uploadHerbalMedicine(medicineModel) }
    fun uploadGeneral(medicineModel: MedicineModel){ repo.uploadGeneral(medicineModel) }
    fun updateHerbal(medicineModel: MedicineModel){ repo.updateHerbal(medicineModel) }
    fun updateMedicine(medicineModel: MedicineModel){ repo.updateMedicine(medicineModel) }
    fun updateGeneral(medicineModel: MedicineModel){ repo.updateGeneral(medicineModel) }
    fun updateCosmetics(medicineModel: MedicineModel){ repo.updateCosmetics(medicineModel) }
    fun getMedicine(): Task<QuerySnapshot> { return repo.GetMedicine() }
    fun getHerbal(): Task<QuerySnapshot> { return repo.getHerbal() }
    fun getGeneral(): Task<QuerySnapshot> { return repo.getGeneral() }
    fun getCosmetics(): Task<QuerySnapshot> { return repo.getCosmetics() }

}