package com.raqun.android.ui.products

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raqun.android.databinding.ItemProductsGridBinding
import com.raqun.android.model.Product

/**
 * Created by tyln on 04/10/2017.
 */
class ProductsAdapter(private val products: List<Product>,
                      private val itemClick: (product: Product) -> Unit)
    : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(ItemProductsGridBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            val product = products[position]
            it.bind(product)
            it.itemView.setOnClickListener {
                itemClick(product)
            }
        }
    }

    override fun getItemCount(): Int = products.size

    class ViewHolder(private val itemBinding: ItemProductsGridBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(product: Product) {
            itemBinding.product = product
            itemBinding.executePendingBindings()
        }
    }
}