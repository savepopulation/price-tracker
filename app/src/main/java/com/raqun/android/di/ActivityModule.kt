package com.raqun.android.di

import com.raqun.android.ui.add.AddProductActivity
import com.raqun.android.ui.contact.ContactActivity
import com.raqun.android.ui.content.ContentActivity
import com.raqun.android.ui.login.LoginActivity
import com.raqun.android.ui.logout.LogoutActivity
import com.raqun.android.ui.main.MainActivity
import com.raqun.android.ui.product.ProductActivity
import com.raqun.android.ui.products.ProductsActivity
import com.raqun.android.ui.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tyln on 26/07/2017.
 */
@Module
internal abstract class ActivityModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideMainActivityContributor(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideLoginActivityContributor(): LoginActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideRegisterActivityContributor(): RegisterActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideContactActivityContributor(): ContactActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideLogoutActivityContributor(): LogoutActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideContentActivityContributor(): ContentActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideProductActivityContributor(): ProductActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideAddProductActivityContributor(): AddProductActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
    @ActivityScope
    abstract fun provideProductsActivityContributor(): ProductsActivity
}