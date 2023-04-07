package com.example.sneakers.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sneakers.databinding.FragmentSneakerDetailBinding
import com.example.sneakers.ui.ViewModel.SharedDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SneakerDetailFragment : Fragment() {

    private lateinit var binding: FragmentSneakerDetailBinding
    private lateinit var viewModel: SharedDataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSneakerDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedDataViewModel::class.java]
        setSneakersDetails()
        return binding.root
    }

    private fun setSneakersDetails() {
        binding.apply {
            Glide.with(root.context)
                .load(
                    viewModel.slectedSneakerDetail.value?.media?.imageUrl
                )
                .centerCrop()
                .into(sneakerImage)
            sneakerName.text = viewModel.slectedSneakerDetail.value?.name
            sneakerTitle.text = viewModel.slectedSneakerDetail.value?.title
            sneakerYear.text = viewModel.slectedSneakerDetail.value?.year.toString()
            sneakerPrice.text = buildString {
                append("\$ ")
                append(viewModel.slectedSneakerDetail.value?.retailPrice.toString())
            }
            btnAddToCart.setOnClickListener {
                viewModel.slectedSneakerDetail.value?.let { it1 -> viewModel.addSneakerToCart(it1.id) }
            }
        }

    }


}