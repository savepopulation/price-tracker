package com.raqun.android.ui.product.detail

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.raqun.android.data.DataBean
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import javax.inject.Inject

/**
 * Created by tyln on 06/11/2017.
 */
class DetailViewModel @Inject constructor() : ViewModel() {

    private val productLiveData = MediatorLiveData<DataBean<Product>>()

    private val productIdLiveData = MutableLiveData<String>()

    init {
        productLiveData.addSource(productIdLiveData, { productId: String? ->
            productId?.run {
                getProductDetail(this)
            }
        })
    }

    fun setProduct(product: Product?) {
        if (product == null) {
            return
        }

        if (product == productLiveData.value?.getData()) {
            return
        }

        productLiveData.value = UiDataBean.success(product)
    }

    fun setProductId(productId: String?) {
        if (productId == null) {
            return
        }

        if (productId == productIdLiveData.value) {
            return
        }

        productIdLiveData.value = productId
    }

    fun getProduct() = productLiveData

    private fun getProductDetail(productId: String) {
        Log.e("fetching from network", "fetcing")
    }
}