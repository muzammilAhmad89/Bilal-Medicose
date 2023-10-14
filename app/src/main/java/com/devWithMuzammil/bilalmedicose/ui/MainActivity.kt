package com.devWithMuzammil.bilalmedicose.ui
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.databinding.ActivityMainBinding
import com.devWithMuzammil.bilalmedicose.fragments.AddFragment
import com.devWithMuzammil.bilalmedicose.fragments.CosmaticFragment
import com.devWithMuzammil.bilalmedicose.fragments.GeneralFragment
import com.devWithMuzammil.bilalmedicose.fragments.HerbalFragment
import com.devWithMuzammil.bilalmedicose.fragments.MedicineFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext=this@MainActivity

        // To disable night theme in the application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val transaction = supportFragmentManager.beginTransaction()

            when (menuItem.itemId) {
                R.id.herbal -> {
                    transaction.replace(R.id.container, HerbalFragment())
                }
                R.id.medicine -> {
                    transaction.replace(R.id.container, MedicineFragment())
                }
                R.id.cosmatic -> {
                    transaction.replace(R.id.container, CosmaticFragment())
                    Log.d("MainActivity", "Cosmatics Fragment replaced")
                }
                R.id.general -> {
                    transaction.replace(R.id.container, GeneralFragment())
                }

                // Add more cases for other menu items as needed
            }


            transaction.addToBackStack(null)
            transaction.commit()
            true
        }


        // Set the initial fragment
        val initialFragment = HerbalFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container, initialFragment).commit()
        binding.add.setOnClickListener { view ->
            startActivity(Intent(mContext,ActivityAdd::class.java))
        }
    }
}
