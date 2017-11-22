package com.raqun.android.ui.product.alarms

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Alarm
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 06/11/2017.
 */
class AlarmsViewModel @Inject constructor(private val productRepository: ProductRepository,
                                          private val userRepository: UserRepository)
    : ViewModel() {

    private val productIdLiveData = MutableLiveData<String>()

    private val alarmsLiveData = MediatorLiveData<DataBean<List<Alarm>>>()

    init {
        alarmsLiveData.addSource(productIdLiveData, { productId: String? ->
            if (isUserLoggedIn() && productId != null) {
                getAlarmsOfProduct(productId)
            }
        })
    }

    fun getAlarms() = alarmsLiveData

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun setProductId(productId: String?) {
        if (productId == null) {
            return
        }

        if (productId == productIdLiveData.value) {
            return
        }

        productIdLiveData.value = productId
    }

    private fun getAlarmsOfProduct(productId: String) {
        alarmsLiveData.value = UiDataBean.fetching(null)
        productRepository.getAlarms(AlarmRequest(productId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            alarmsLiveData.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            alarmsLiveData.value = UiDataBean.error(null, it.getError())
                        }
                )
    }
}