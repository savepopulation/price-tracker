package com.raqun.android.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View
import com.raqun.android.ui.add.AddProductViewModel
import com.raqun.android.ui.contact.ContactViewModel
import com.raqun.android.ui.content.ContentViewModel
import com.raqun.android.ui.login.LoginViewModel
import com.raqun.android.ui.logout.LogoutViewModel
import com.raqun.android.ui.main.favorites.FavoritesFragment
import com.raqun.android.ui.main.favorites.FavoritesViewModel
import com.raqun.android.ui.main.home.HomeViewModel
import com.raqun.android.ui.main.more.MoreViewModel
import com.raqun.android.ui.main.notifications.NotificationsViewModel
import com.raqun.android.ui.product.ProductViewModel
import com.raqun.android.ui.product.alarms.AlarmsViewModel
import com.raqun.android.ui.product.detail.DetailViewModel
import com.raqun.android.ui.products.ProductsViewModel
import com.raqun.android.ui.register.RegisterViewModel
import com.raqun.android.viewmodel.VMFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by tyln on 26/07/2017.
 */
@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoreViewModel::class)
    abstract fun bindMoreViewModel(moreViewModel: MoreViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(notificationsViewModel: NotificationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactViewModel::class)
    abstract fun bindContactViewModel(contactViewModel: ContactViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LogoutViewModel::class)
    abstract fun bindLogoutViewModel(logoutViewModel: LogoutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContentViewModel::class)
    abstract fun bindContentViewModel(contentViewModel: ContentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindProductViewModel(productViewModel: ProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddProductViewModel::class)
    abstract fun bindAddProductViewModel(addProductViewModel: AddProductViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductsViewModel::class)
    abstract fun bindProductsViewModel(productsViewModel: ProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AlarmsViewModel::class)
    abstract fun bindAlarmsViewModel(alarmsViewModel: AlarmsViewModel): ViewModel


    // Factory
    @Binds abstract fun bindViewModelFactory(vmFactory: VMFactory): ViewModelProvider.Factory
}