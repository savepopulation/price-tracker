package com.raqun.android.ui.add

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.UiDataBean
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.data.DataBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 18/09/2017.
 */
class AddProductViewModel @Inject constructor(private val productRepository: ProductRepository,
                                              private val userRepository: UserRepository)
    : ViewModel() {

    private val addProduct = MutableLiveData<DataBean<Boolean>>()

    fun getAddProduct() = addProduct

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun addProduct(url: String) {
        addProduct.value = UiDataBean.fetching(null)
        productRepository.addProduct(AddProductRequest(url))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onComplete = {
                            addProduct.value = UiDataBean.success(true)
                        },
                        onError = {
                            addProduct.value = UiDataBean.error(false, it.getError())
                        }
                )
    }
}