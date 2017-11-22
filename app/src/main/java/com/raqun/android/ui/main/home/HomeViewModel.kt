package com.raqun.android.ui.main.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.WebApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 29/07/2017.
 */
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val recentProductsLiveData = MutableLiveData<DataBean<List<Product>>>()
    private val topProductsLiveData = MutableLiveData<DataBean<List<Product>>>()
    private val discountedProductsLiveData = MutableLiveData<DataBean<List<Product>>>()
    private val topFollowedAppsLiveData = MutableLiveData<DataBean<List<WebApp>>>()

    init {
        getTopProducts()
        getRecentProducts()
        getDiscountedProducts()
        getTopFollowedApps()
    }

    fun getRecent() = recentProductsLiveData

    fun getTop() = topProductsLiveData

    fun getDiscounted() = discountedProductsLiveData

    fun getApps() = topFollowedAppsLiveData


    // TODO improve this implementation

    fun getTopProducts() {
        topProductsLiveData.postValue(UiDataBean.fetching(null))
        productRepository.getTopFollowedProducts(Page())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            topProductsLiveData.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            topProductsLiveData.value = UiDataBean.error(null, it.getError())
                        }
                )
    }

    private fun getRecentProducts() {
        recentProductsLiveData.postValue(UiDataBean.fetching(null))
        productRepository.getRecentFollowedProducts(Page())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            recentProductsLiveData.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            recentProductsLiveData.value = UiDataBean.error(null, it.getError())
                        }
                )
    }

    private fun getDiscountedProducts() {
        discountedProductsLiveData.postValue(UiDataBean.fetching(null))
        productRepository.getDiscountedProducts(Page())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            discountedProductsLiveData.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            discountedProductsLiveData.value = UiDataBean.error(null, it.getError())
                        }
                )
    }

    private fun getTopFollowedApps() {
        topFollowedAppsLiveData.postValue(UiDataBean.fetching(null))
        productRepository.getTopWebApps(Page())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            topFollowedAppsLiveData.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            discountedProductsLiveData.value = UiDataBean.error(null, it.getError())
                        }
                )
    }
}
