package com.devWithMuzammil.bilalmedicose.data

import android.content.Context
import android.widget.Toast
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.PurchaserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Repo(val context: Context) {

    private val db = Firebase.firestore
    private var HERBAL_COLLECTION=db.collection("Herbal")
    private var MEDICINE_COLLECTION=db.collection("Medicine")
    private var COSMETICS_COLLECTION=db.collection("Cosmetics")
    private var GENERAL_COLLECTION=db.collection("General")
    private var PURCHASER_COLLECTION=db.collection("Purchasers")



    fun getHerbal(): Task<QuerySnapshot> {

        return HERBAL_COLLECTION.get()

    }
     fun GetMedicine(): Task<QuerySnapshot> {

        return MEDICINE_COLLECTION.get()

    }
    fun getCosmetics(): Task<QuerySnapshot> {

        return COSMETICS_COLLECTION.get()

    }
    fun getGeneral(): Task<QuerySnapshot> {

        return GENERAL_COLLECTION.get()

    }
    fun savePurchaser(purchaserModel: PurchaserModel){
        try {
            // Add the model to Firestore
            PURCHASER_COLLECTION.add(purchaserModel)
                .addOnSuccessListener { documentReference ->
                    val documentId = documentReference.id
                    purchaserModel.Id = documentId

                    PURCHASER_COLLECTION.document(documentId).set(purchaserModel)
                }

            } catch (e: Exception) {
            Toast.makeText(context,""+e,Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
    fun getPurchaser():Task<QuerySnapshot>{
        return PURCHASER_COLLECTION.get()

    }

    fun uploadHerbalMedicine(medicineModel: MedicineModel) {

        // Set the data of the new document to the MedicineModel object
        HERBAL_COLLECTION.add(medicineModel)
            .addOnSuccessListener { documentReference ->
                val documentId = documentReference.id
                medicineModel.id = documentId

                HERBAL_COLLECTION.document(documentId).set(medicineModel)
            }

    }
    fun uploadMedicine(medicineModel: MedicineModel) {
        // Set the data of the new document to the MedicineModel object
        MEDICINE_COLLECTION.add(medicineModel)
            .addOnSuccessListener { documentReference ->
                val documentId = documentReference.id
                medicineModel.id = documentId

                MEDICINE_COLLECTION.document(documentId).set(medicineModel)
            }

    }
    fun uploadCosmetics(medicineModel: MedicineModel) {

        // Set the data of the new document to the MedicineModel object
        COSMETICS_COLLECTION.add(medicineModel)
            .addOnSuccessListener { documentReference ->
                val documentId = documentReference.id
                medicineModel.id = documentId

                COSMETICS_COLLECTION.document(documentId).set(medicineModel)
            }

    }
    fun uploadGeneral(medicineModel: MedicineModel) {
        // Set the data of the new document to the MedicineModel object
        GENERAL_COLLECTION.add(medicineModel)
            .addOnSuccessListener { documentReference ->
                val documentId = documentReference.id
                medicineModel.id = documentId

                GENERAL_COLLECTION.document(documentId).set(medicineModel)
            }

    }

}