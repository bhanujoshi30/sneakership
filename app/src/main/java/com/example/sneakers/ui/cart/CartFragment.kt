package com.example.sneakers.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sneakers.databinding.FragmentCartBinding
import com.example.sneakers.ui.ViewModel.SharedDataViewModel
import com.example.sneakers.ui.adapters.CartSneakersAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToLong

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var viewModel: SharedDataViewModel
    private lateinit var binding: FragmentCartBinding
    private var adapter: CartSneakersAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedDataViewModel::class.java]
        binding.recyclerViewCart.layoutManager = GridLayoutManager(requireContext(), 1)
        setObservers()
        initUi()
        return binding.root
    }

    private fun setObservers() {
        viewModel.cartSneakers.observe(viewLifecycleOwner) { sneakers ->
            adapter = CartSneakersAdapter(sneakers) {
                viewModel.removeSneakerFromCart(it.id)
            }
            binding.recyclerViewCart.adapter = adapter
        }
        viewModel.sumOfCart.observe(viewLifecycleOwner) { totalPrice ->
            binding.cartSubTotal.text = java.lang.StringBuilder("Subtotal: $").append(totalPrice)
            binding.cartTotalTaxes.text = java.lang.StringBuilder("Taxes and Charges: $")
                .append(totalPrice.times(0.18).roundToLong())
            binding.grandTotal.text =
                java.lang.StringBuilder("Total: $").append(totalPrice.times(1.18).roundToLong())
        }

    }

    private fun initUi() {
        binding.toolbar.apply {
            title = "Cart"
        }
    }

}