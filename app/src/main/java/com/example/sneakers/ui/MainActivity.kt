package com.example.sneakers.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sneakers.R
import com.example.sneakers.databinding.ActivityMainBinding
import com.example.sneakers.ui.ViewModel.SharedDataViewModel
import com.example.sneakers.ui.cart.CartFragment
import com.example.sneakers.ui.detail.SneakerDetailFragment
import com.example.sneakers.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HomeFragment.IHomeFragmentSneakerDetail {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SharedDataViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SharedDataViewModel::class.java]
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        initUi()
    }


    private fun initUi() {
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


    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    override fun sneakerClickForDetails() {
        replaceFragment(SneakerDetailFragment())
    }

}