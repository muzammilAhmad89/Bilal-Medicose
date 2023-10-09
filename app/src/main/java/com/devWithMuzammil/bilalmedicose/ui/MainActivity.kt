package com.devWithMuzammil.bilalmedicose.ui
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.devWithMuzammil.bilalmedicose.AddFragment
import com.devWithMuzammil.bilalmedicose.CosmaticsFragment
import com.devWithMuzammil.bilalmedicose.GeneralFragment
import com.devWithMuzammil.bilalmedicose.HerbalFragment
import com.devWithMuzammil.bilalmedicose.MedicineFragment
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To disable night theme in the application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val transaction = supportFragmentManager.beginTransaction()

            when (menuItem.itemId) {
                R.id.herbal -> {
                    transaction.replace(R.id.container,HerbalFragment())
                }
                R.id.medicine -> {
                    transaction.replace(R.id.container, MedicineFragment())
                }
                R.id.cosmatics -> {
                    transaction.replace(R.id.container, CosmaticsFragment())
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
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, AddFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
