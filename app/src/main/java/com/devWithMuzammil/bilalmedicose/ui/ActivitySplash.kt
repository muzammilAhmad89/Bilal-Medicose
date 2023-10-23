package com.devWithMuzammil.bilalmedicose.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.devWithMuzammil.bilalmedicose.Models.MedicineModel
import com.devWithMuzammil.bilalmedicose.Models.ProductViewModel
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.SharedPrefManager
import kotlinx.coroutines.launch
import java.util.Timer
import kotlin.concurrent.schedule

class ActivitySplash : AppCompatActivity() {
    private val productViewModel:ProductViewModel by viewModels()
    private lateinit var sharedPrefManager:SharedPrefManager
    private lateinit var mContext:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        mContext=this@ActivitySplash

        sharedPrefManager= SharedPrefManager(this@ActivitySplash)

        Timer().schedule(1000) {


            lifecycleScope.launch {

                productViewModel.getMedicine().addOnCompleteListener { task->
                    if(task.isSuccessful){
                        sharedPrefManager.putMedicineList(task.result.map { it.toObject(MedicineModel::class.java) })


                        lifecycleScope.launch {

                            productViewModel.getHerbal().addOnCompleteListener { task->
                                if(task.isSuccessful){
                                    sharedPrefManager.putHerbalList(task.result.map { it.toObject(MedicineModel::class.java) })


                                    lifecycleScope.launch {
                                        productViewModel.getCosmetics().addOnCompleteListener { task->
                                            if(task.isSuccessful){
                                                sharedPrefManager.putCosmeticsList(task.result.map { it.toObject(MedicineModel::class.java) })




                                                lifecycleScope.launch {
                                                    productViewModel.getGeneral().addOnCompleteListener { task->
                                                        if(task.isSuccessful){
                                                            sharedPrefManager.putGeneralList(task.result.map { it.toObject(MedicineModel::class.java) })
                                                            startActivity(Intent(this@ActivitySplash,MainActivity::class.java))
                                                            finish()
                                                        }


                                                    }.addOnFailureListener {e->
                                                        Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
                                                    }

                                                }


                                            }


                                        }.addOnFailureListener {e->
                                            Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
                                        }

                                    }







                                }


                            }.addOnFailureListener {e->
                                Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
                            }

                        }

                    }


                }.addOnFailureListener {e->
                    Toast.makeText(mContext, ""+e.message, Toast.LENGTH_SHORT).show()
                }

            }




        }
    }
}