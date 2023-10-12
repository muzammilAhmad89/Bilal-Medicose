package com.devWithMuzammil.bilalmedicose.data

import android.content.Context
import android.widget.Toast
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
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



    suspend fun getHerbal(): Task<QuerySnapshot> {

        return HERBAL_COLLECTION.get()

    }
    suspend fun GetMedicine(): Task<QuerySnapshot> {

        return MEDICINE_COLLECTION.get()

    }
    suspend fun getCosmetics(): Task<QuerySnapshot> {

        return COSMETICS_COLLECTION.get()

    }
    suspend fun getGeneral(): Task<QuerySnapshot> {

        return GENERAL_COLLECTION.get()

    }
    fun savePurchaserToFirestore(name:String,phone:String,address:String): Boolean {
        try {
            // Convert the model to a Map (Firestore requires a Map)
            val modelMap = mapOf(
                "dealerName" to name,
                "phoneName" to phone,
                "dealerAddress" to address
            )

            // Add the model to Firestore
            PURCHASER_COLLECTION.add(modelMap)
            return true
        } catch (e: Exception) {
            Toast.makeText(context,""+e,Toast.LENGTH_SHORT).show()
            e.printStackTrace()
            return false
        }
    }
    fun uploadHerbalMedicine(medicineModel: MedicineModel): Boolean {
        var success = false

        // Create a reference to a new document in the "HERBAL_COLLECTION" collection
        val documentReference = HERBAL_COLLECTION.document()

        // Set the data of the new document to the MedicineModel object
        documentReference.set(medicineModel)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // The document was successfully created
                    success = true
                }
            }

        return success
    }

}