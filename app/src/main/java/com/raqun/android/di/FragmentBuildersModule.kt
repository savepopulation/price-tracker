package com.raqun.android.di

import android.text.LoginFilter
import com.raqun.android.ui.add.AddProductFragment
import com.raqun.android.ui.contact.ContactFragment
import com.raqun.android.ui.content.ContentFragment
import com.raqun.android.ui.login.LoginFragment
import com.raqun.android.ui.logout.LogoutFragment
import com.raqun.android.ui.main.favorites.FavoritesFragment
import com.raqun.android.ui.main.home.HomeFragment
import com.raqun.android.ui.main.more.MoreFragment
import com.raqun.android.ui.main.notifications.NotificationsFragment
import com.raqun.android.ui.product.ProductFragment
import com.raqun.android.ui.product.alarms.AlarmsFragment
import com.raqun.android.ui.product.detail.DetailFragment
import com.raqun.android.ui.products.ProductsFragment
import com.raqun.android.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tyln on 26/07/2017.
 */
@Module
internal abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeMoreFragment(): MoreFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeNotificationsFragment(): NotificationsFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeFavoritesFragment(): FavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeLoginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeRegisterFragment(): RegisterFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeContactFragment(): ContactFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeLogoutFragment(): LogoutFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeContentFragment(): ContentFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeProductFragment(): ProductFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeAddProductFragment(): AddProductFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeProductsFragment(): ProductsFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributeDetailsFragment(): DetailFragment

    @FragmentScope
    @ContributesAndroidInjector abstract fun contributesAlarmsFragment(): AlarmsFragment
}