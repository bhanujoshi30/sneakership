package com.example.sneakers.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakers.R
import com.example.sneakers.databinding.FragmentCartBinding
import com.example.sneakers.databinding.FragmentHomeBinding
import com.example.sneakers.ui.adapters.CartSneakersAdapter
import com.example.sneakers.ui.adapters.SneakerAdapter
import com.example.sneakers.ui.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentCartBinding
    private var adapter: CartSneakersAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.recyclerViewCart.layoutManager = GridLayoutManager(requireContext(), 1)
        observers()
        referencesUi()
        return binding.root
    }
    private fun observers() {
        viewModel.getSneakers()
        viewModel.sneakers.observe(viewLifecycleOwner) { sneakers ->
            adapter = CartSneakersAdapter(sneakers)
            binding.recyclerViewCart.adapter = adapter
        }
    }
    private fun referencesUi() {
        binding.toolbar.apply {
            title = "Cart"

        }
    }

}