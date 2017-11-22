package com.raqun.android.ui.main.notifications

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.UserRepository
import com.raqun.android.model.Notification
import com.raqun.android.model.Page
import com.raqun.android.model.UiDataBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import com.raqun.android.data.Error
import com.raqun.android.extensions.getError
import io.reactivex.schedulers.Schedulers

/**
 * Created by tyln on 31/07/2017.
 */
class NotificationsViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val notificationsLiveData = MutableLiveData<DataBean<List<Notification>>>()

    init {
        fetchNotifications()
    }

    fun getNotifications() = notificationsLiveData

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    private fun fetchNotifications() {
        if (!isUserLoggedIn()) {
            return
        }

        notificationsLiveData.value = UiDataBean.fetching(null)
        userRepository.getNotifications(Page())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = {
                            notificationsLiveData.value = UiDataBean.success(it.items)
                        },
                        onError = {
                            notificationsLiveData.value = UiDataBean.error(null, it.getError())
                        }
                )
    }
}