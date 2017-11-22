package com.raqun.android.ui.main.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.raqun.android.databinding.ItemWebAppsBinding
import com.raqun.android.model.WebApp

/**
 * Created by tyln on 13/08/2017.
 */
class HomeWebAppsAdapter(private val apps: List<WebApp>) : RecyclerView.Adapter<HomeWebAppsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return ViewHolder(ItemWebAppsBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(apps[position])
    }

    override fun getItemCount(): Int = apps.size

    class ViewHolder(private val itemBinding: ItemWebAppsBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(app: WebApp) {
            itemBinding.webApp = app
            itemBinding.executePendingBindings()
        }
    }
}