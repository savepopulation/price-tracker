package com.raqun.android.di

import com.raqun.android.fcm.RegisterTokenService
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tyln on 26/10/2017.
 */
@Module
internal abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract fun provideRegisterTokenServiceContributor(): RegisterTokenService
}