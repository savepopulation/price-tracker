package com.raqun.android.binding

import android.content.res.Resources
import android.databinding.BindingAdapter
import android.support.annotation.DrawableRes
import android.support.annotation.IntegerRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.DataState
import com.raqun.android.extensions.formatToPrice
import com.raqun.android.model.*
import com.raqun.android.model.AlarmType.PRICE_DROP
import com.raqun.android.ui.NavigationController
import com.raqun.android.ui.main.home.HomeProductsAdapter
import com.raqun.android.ui.main.home.HomeWebAppsAdapter
import com.raqun.android.ui.main.notifications.NotificationsAdapter
import com.raqun.android.ui.product.alarms.AlarmsAdapter
import com.raqun.android.ui.products.ProductsAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tyln on 03/08/2017.
 */
object CommonBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = *arrayOf("homeProducts", "navigator"), requireAll = true)
    fun bindHomeProducts(recyclerView: RecyclerView,
                         products: List<Product>?,
                         navigationController: NavigationController?) {
        products?.let {
            recyclerView.adapter = HomeProductsAdapter(it) { product ->
                navigationController?.navigateToProduct(product)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("products", "navigator"), requireAll = true)
    fun bindProducts(recyclerView: RecyclerView,
                     products: List<Product>?,
                     navigationController: NavigationController?) {
        products?.let {
            recyclerView.adapter = ProductsAdapter(it) { product ->
                navigationController?.navigateToProduct(product)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("alarms"), requireAll = true)
    fun bindAlarms(recyclerView: RecyclerView,
                   alarms: List<Alarm>?) {
        alarms?.let {
            recyclerView.adapter = AlarmsAdapter(it) {

            }
        }
    }

    @JvmStatic
    @BindingAdapter("webApps")
    fun bindWebApps(recyclerView: RecyclerView, webApps: List<WebApp>?) {
        webApps?.let {
            recyclerView.adapter = HomeWebAppsAdapter(it)
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("notifications", "navigator"), requireAll = true)
    fun bindNotifications(recyclerView: RecyclerView,
                          notifications: List<Notification>?,
                          navigationController: NavigationController?) {
        notifications?.let {
            recyclerView.adapter = NotificationsAdapter(it) { productId ->
                navigationController?.navigateToProduct(productId)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("date", "isUpdate"), requireAll = false)
    fun bindDateFormat(textView: TextView, dateStr: String?, isUpdate: Boolean?) {
        var dateText = "-"

        dateStr?.let {
            var sdf = SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT)
            val date: Date = sdf.parse(dateStr)
            sdf = SimpleDateFormat(Constants.NOTIFICATION_DATE_FORMAT)
            dateText = sdf.format(date)
        }

        try {
            dateText = if (isUpdate != null && isUpdate) textView.context.getString(R.string.generic_text_update_date, dateText) else dateText
            textView.text = dateText
        } catch (e: Resources.NotFoundException) {
            textView.text = dateText
        }
    }

    @JvmStatic
    @BindingAdapter("notificationProduct")
    fun bindNotification(textView: TextView, product: Product?) {
        if (product != null) {
            textView.text = product.name
        } else {
            textView.text = textView.context.getString(R.string.app_name)
        }
    }

    @JvmStatic
    @BindingAdapter(value = *arrayOf("price", "currency"), requireAll = true)
    fun bindPrice(textView: TextView, price: Double?, currency: String?) {
        val priceRepresentation = price.formatToPrice()
        if (priceRepresentation != null && currency != null) {
            textView.text = with(StringBuilder()) {
                append(priceRepresentation)
                append(" ")
                append(currency)
                toString()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("alarmInfo")
    fun bindNotification(textView: TextView, alarmId: Int) {
        when (alarmId) {
            PRICE_DROP.id -> textView.text = textView.context.getString(R.string.information_alarm_price_drop)
        }
    }
}