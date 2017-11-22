package com.raqun.android.ui.main.favorites

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.raqun.android.RaqunApp
import com.raqun.android.data.DataBean
import com.raqun.android.data.Error
import com.raqun.android.data.source.UserRepository
import com.raqun.android.di.ViewModelKey
import com.raqun.android.extensions.getError
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 31/07/2017.
 */
class FavoritesViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val products = MutableLiveData<DataBean<List<Product>>>()

    init {
        getFavoriteProducts()
    }

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun getProducts() = products

    private fun getFavoriteProducts() {
        if (!isUserLoggedIn()) {
            return
        }

        products.value = UiDataBean.fetching(null)
        userRepository.getFavoriteProducts(Page(0, 15))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            products.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            products.value = UiDataBean.error(null, it.getError())
                        }
                )
    }
}