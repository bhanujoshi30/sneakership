package com.example.sneakers.ui

//import com.example.sneakers.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.sneakers.R
import com.example.sneakers.databinding.ActivityMainBinding
import com.example.sneakers.ui.cart.CartFragment
import com.example.sneakers.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            elevation = 0f
            title = getString(R.string.app_name_allcaps)
        }

        replaceFragment(HomeFragment())
        binding.bottomNavigationView.selectedItemId = R.id.home
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.cart -> {
                    replaceFragment(CartFragment())
                    true
                }
                else -> false
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("bhanu","dsd" )

        return super.onCreateOptionsMenu(menu)

    }


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}