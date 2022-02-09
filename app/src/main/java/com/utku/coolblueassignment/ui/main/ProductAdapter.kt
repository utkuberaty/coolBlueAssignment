package com.utku.coolblueassignment.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.utku.coolblueassignment.data.entitity.Product
import com.utku.coolblueassignment.databinding.ItemProductBinding

class ProductAdapter : PagingDataAdapter<Product, ProductAdapter.ViewHolder>(ProductComparator) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    class ViewHolder(private val viewBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(product: Product?) {
            product?.let {
                viewBinding.apply {
                    productName.text = it.productName
                    productImageView.load(it.productImage)
                    productReviewTextView.text =
                        "${it.reviewInformation.reviewSummary.reviewCount} reviews"
                    productPriceTextView.text = it.salesPriceIncVat.toString()
                    productRating.rating = it.reviewInformation.reviewSummary.reviewAverage
                }
            }
        }

    }
}

object ProductComparator : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        // Id is unique.
        return oldItem.productId == newItem.productId
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
