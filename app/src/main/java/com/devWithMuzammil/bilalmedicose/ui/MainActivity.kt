package com.devWithMuzammil.bilalmedicose.ui
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.devWithMuzammil.bilalmedicose.R
import com.devWithMuzammil.bilalmedicose.databinding.ActivityMainBinding
import com.devWithMuzammil.bilalmedicose.fragments.AddFragment
import com.devWithMuzammil.bilalmedicose.fragments.CosmaticFragment
import com.devWithMuzammil.bilalmedicose.fragments.GeneralFragment
import com.devWithMuzammil.bilalmedicose.fragments.HerbalFragment
import com.devWithMuzammil.bilalmedicose.fragments.MedicineFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mContext: Context
    private var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext=this@MainActivity

        // To disable night theme in the application
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupNavigationDrawer()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem -> // Handle navigation item clicks here
            val itemId = menuItem.itemId

            if (itemId == R.id.purchasers) {
                //startActivity(Intent(mContext,ActivityResult::class.java))
            }
            // Close the navigation drawer
            val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            val transaction = supportFragmentManager.beginTransaction()

            when (menuItem.itemId) {
                R.id.herbal -> {
                    transaction.replace(R.id.container, HerbalFragment())
                }
                R.id.medicine -> {
                    transaction.replace(R.id.container, MedicineFragment())
                }
                R.id.cosmetic -> {
                    transaction.replace(R.id.container, CosmaticFragment())
                    //Toast.makeText(mContext, "Cosmetics are here", Toast.LENGTH_SHORT).show()
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
        binding.add.setOnClickListener {
            startActivity(Intent(mContext,ActivityAdd::class.java))
        }
    }
    private fun setupNavigationDrawer() {
        val hamburgerIcon = findViewById<ImageView>(R.id.imgDrawer)
        drawerLayout = findViewById(R.id.drawerLayout)
        hamburgerIcon.setOnClickListener { drawerLayout?.openDrawer(GravityCompat.START) }
        drawerLayout?.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // Called when the drawer is sliding
            }

            override fun onDrawerOpened(drawerView: View) {
                // Called when the drawer is fully opened
            }

            override fun onDrawerClosed(drawerView: View) {
                // Called when the drawer is fully closed
            }

            override fun onDrawerStateChanged(newState: Int) {
                // Called when the state of the drawer changes
            }
        })
    }
}
