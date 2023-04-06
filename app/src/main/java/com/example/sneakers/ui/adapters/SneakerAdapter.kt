package com.example.sneakers.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakers.databinding.HomeSneakerAdapterBinding
import com.example.sneakers.models.Sneaker
import com.example.sneakers.utils.SortingOrder

class SneakerAdapter(private var sneakers: List<Sneaker>) :
    RecyclerView.Adapter<SneakerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeSneakerAdapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sneaker = sneakers[position]
        with(holder.binding) {
            Glide.with(root.context)
                .load(sneaker.media?.imageUrl)
                .centerCrop()
                .into(sneakerImage)
            sneakerName.text = sneaker.name
            sneakerPrice.text = java.lang.StringBuilder("\$").append(sneaker.retailPrice)
        }
    }

    override fun getItemCount() = sneakers.size
    @SuppressLint("NotifyDataSetChanged")
    fun sort(order: Int, search: String) {
        sneakers = when (order) {
            SortingOrder.ASCENDING_NAME.type -> sneakers.sortedBy { it.name }
            SortingOrder.DESCENDING_NAME.type -> sneakers.sortedByDescending { it.name }
            SortingOrder.ASCENDING_PRICE.type-> sneakers.sortedBy { it.retailPrice }
            SortingOrder.DESCENDING_PRICE.type-> sneakers.sortedByDescending { it.retailPrice }
            SortingOrder.QUERY_SEARCH.type-> sneakers.sortedBy { it.name?.contains(search,true) }
            else -> sneakers.sortedBy {it.id}
        }

        notifyDataSetChanged()
    }

    class ViewHolder(val binding: HomeSneakerAdapterBinding) : RecyclerView.ViewHolder(binding.root)
}