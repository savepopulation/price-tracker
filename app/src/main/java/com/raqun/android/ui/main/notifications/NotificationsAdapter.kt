package com.raqun.android.ui.main.notifications

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raqun.android.databinding.ItemNotificationsBinding
import com.raqun.android.model.Notification

/**
 * Created by tyln on 27/09/2017.
 */
class NotificationsAdapter constructor(private val notifications: List<Notification>,
                                       private val itemClick: (key: String) -> Unit)
    : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(ItemNotificationsBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.let {
            val notification = notifications[position]
            it.bind(notification)
            it.itemView.setOnClickListener({
                itemClick.invoke(notification.key)
            })
        }
    }

    override fun getItemCount(): Int = notifications.size

    class ViewHolder(private val itemBinding: ItemNotificationsBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(notification: Notification) {
            itemBinding.notification = notification
            itemBinding.executePendingBindings()
        }
    }
}