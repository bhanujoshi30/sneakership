package com.example.sneakers.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakers.databinding.HomeSneakerAdapterBinding
import com.example.sneakers.models.Sneaker
import com.example.sneakers.utils.SneakerAdapterClickType
import com.example.sneakers.utils.SneakerSortingOrder

class SneakerAdapter(
    private var sneakers: List<Sneaker>,
    private val sneakerClickListener: (Sneaker, SneakerAdapterClickType) -> Unit
) :
    RecyclerView.Adapter<SneakerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeSneakerAdapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(sneakers[position], sneakerClickListener)
    }

    override fun getItemCount() = sneakers.size

    @SuppressLint("NotifyDataSetChanged")
    fun sort(order: Int) {
        sneakers = when (order) {
            SneakerSortingOrder.ASCENDING_NAME.type -> sneakers.sortedBy { it.name }
            SneakerSortingOrder.DESCENDING_NAME.type -> sneakers.sortedByDescending { it.name }
            SneakerSortingOrder.ASCENDING_PRICE.type -> sneakers.sortedBy { it.retailPrice }
            SneakerSortingOrder.DESCENDING_PRICE.type -> sneakers.sortedByDescending { it.retailPrice }
            else -> sneakers.sortedBy { it.id }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filter(search: String) {
        sneakers =
            sneakers.filter { sneaker: Sneaker -> sneaker.name?.contains(search, true) ?: false }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(sneakersUpdateList: List<Sneaker>) {
        sneakers = sneakersUpdateList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: HomeSneakerAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(
            sneaker: Sneaker,
            clickListner: (Sneaker, SneakerAdapterClickType) -> Unit
        ) {
            with(binding) {
                Glide.with(root.context)
                    .load(sneaker.media?.imageUrl)
                    .centerCrop()
                    .into(sneakerImage)
                sneakerName.text = sneaker.name
                sneakerPrice.text = java.lang.StringBuilder("\$").append(sneaker.retailPrice)

                root.setOnClickListener {
                    clickListner(sneaker, SneakerAdapterClickType.OPEN_DETAILS)
                }
                addCartBtn.setOnClickListener {
                    clickListner(sneaker, SneakerAdapterClickType.ADD_TO_CART)
                }
            }
        }
    }
}