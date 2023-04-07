package com.example.sneakers.ui.adapters

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sneakers.databinding.CartAdapterBinding
import com.example.sneakers.models.Sneaker

class CartSneakersAdapter(
    private var sneakers: List<Sneaker>,
    private val cartSneakerClickListener: (Sneaker) -> Unit
) :
    RecyclerView.Adapter<CartSneakersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CartAdapterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(sneakers[position], cartSneakerClickListener)
    }

    override fun getItemCount() = sneakers.size


    class ViewHolder(val binding: CartAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        private val grayColor = ForegroundColorSpan(Color.GRAY)
        private val blackColor = ForegroundColorSpan(Color.BLACK)

        fun bindData(
            sneaker: Sneaker,
            clickListener: (Sneaker) -> Unit
        ) {
            val priceBuilder = SpannableStringBuilder("\n\$" + sneaker.retailPrice.toString())
            val nameBuilder = SpannableStringBuilder(sneaker.name)
            with(binding) {
                Glide.with(root.context)
                    .load(
                        sneaker.media?.imageUrl
                    )
                    .centerCrop()
                    .into(sneakerImage)
                nameBuilder.setSpan(
                    blackColor,
                    0,
                    nameBuilder.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                priceBuilder.setSpan(
                    grayColor,
                    0,
                    priceBuilder.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                sneakerName.text = SpannableStringBuilder(nameBuilder).append(priceBuilder)
                removeCartBtn.setOnClickListener {
                    clickListener(sneaker)
                }
            }
        }
    }
}