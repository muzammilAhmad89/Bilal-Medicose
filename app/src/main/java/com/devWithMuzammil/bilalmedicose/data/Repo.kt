package com.devWithMuzammil.bilalmedicose.data

import android.content.Context
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