package com.raqun.android.ui.product.alarms

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raqun.android.databinding.ItemAlarmBinding
import com.raqun.android.databinding.ItemProductsGridBinding
import com.raqun.android.model.Alarm
import com.raqun.android.model.Product
import com.raqun.android.ui.products.ProductsAdapter

/**
 * Created by tyln on 14/11/2017.
 */
class AlarmsAdapter(private val alarms: List<Alarm>,
                    private val itemClick: (alarm: Alarm) -> Unit)
    : RecyclerView.Adapter<AlarmsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(ItemAlarmBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            val alarm = alarms[position]
            it.bind(alarm)
            it.itemView.setOnClickListener {
                itemClick(alarm)
            }
        }
    }

    override fun getItemCount(): Int = alarms.size

    class ViewHolder(private val itemBinding: ItemAlarmBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(alarm: Alarm) {
            itemBinding.alarm = alarm
            itemBinding.executePendingBindings()
        }
    }
}