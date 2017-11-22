package com.raqun.android.ui

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.raqun.android.R
import com.raqun.android.ui.login.LoginActivity
import com.raqun.android.ui.main.MainActivity
import com.raqun.android.ui.register.RegisterActivity
import android.support.v4.content.IntentCompat
import android.content.Intent
import android.net.Uri
import android.support.annotation.LayoutRes
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat.startActivity
import com.raqun.android.RaqunApp
import com.raqun.android.model.NotificationType
import com.raqun.android.model.Product
import com.raqun.android.model.ProductListType
import com.raqun.android.session.State
import com.raqun.android.ui.add.AddProductActivity
import com.raqun.android.ui.contact.ContactActivity
import com.raqun.android.ui.content.ContentActivity
import com.raqun.android.ui.logout.LogoutActivity
import com.raqun.android.ui.product.ProductActivity
import com.raqun.android.ui.products.ProductsActivity
import io.reactivex.rxkotlin.toMaybe
import retrofit2.http.Body


/**
 * Created by tyln on 27/07/2017.
 */

class NavigationController(private val activity: Activity) {

    enum class NavigationType {
        BACK, ROOT
    }

    fun close() {
        activity.finish()
    }

    fun navigateToLogin() {
        activity.startActivity(LoginActivity.newIntent(activity))
    }

    fun navigateToRegister() {
        activity.startActivity(RegisterActivity.newIntent(activity))
    }

    fun navigateToHome() {
        MainActivity.newIntent(activity).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }.also {
            activity.startActivity(it)
        }
    }

    fun navigateToContact() {
        activity.startActivity(ContactActivity.newIntent(activity))
    }

    fun navigateToContent(contentId: Int, title: String?) {
        activity.startActivity(ContentActivity.newIntent(activity, contentId, title))
    }

    fun navigateToGooglePlay() {
        val appPackageName = activity.packageName
        try {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)))
        } catch (anfe: android.content.ActivityNotFoundException) {
            activity.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)))
        }

    }

    fun navigateToLogout() {
        LogoutActivity.newIntent(activity).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }.also {
            activity.startActivity(it)
        }
    }

    fun navigateToLogoutForAuthError() {
        RaqunApp.sessionManager.invalidateSession(activity)
    }

    fun navigateToProduct(product: Product?) {
        activity.startActivity(ProductActivity.newIntent(activity, product = product))
    }

    fun navigateToProduct(productId: String?) {
        activity.startActivity(ProductActivity.newIntent(activity, productId = productId))
    }

    fun navigateToProducts(productListType: ProductListType) {
        activity.startActivity(ProductsActivity.newIntent(activity, productListType))
    }

    interface IntentFactory {
        fun createNotificationIntent(action: Int, id: String?): Intent
    }

    class DefaultIntentFactory(private val context: Context) : IntentFactory {

        override fun createNotificationIntent(action: Int, id: String?): Intent {
            return when (action) {
                NotificationType.GENERAL.ordinal -> MainActivity.newIntent(context)
                NotificationType.PRODUCT.ordinal -> ProductActivity.newIntent(context, id)
                else -> MainActivity.newIntent(context)
            }
        }
    }
}