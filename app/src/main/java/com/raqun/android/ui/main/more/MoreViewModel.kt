package com.raqun.android.ui.main.more

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.DataBean
import com.raqun.android.data.source.UserRepository
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.User
import javax.inject.Inject

/**
 * Created by tyln on 31/07/2017.
 */
class MoreViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val userStateLiveData = MutableLiveData<DataBean<Boolean>>()

    private val userDetailsLiveData = MutableLiveData<DataBean<User>>()

    fun init() {
        userStateLiveData.value = UiDataBean.success(userRepository.isUserLoggedIn())
        userDetailsLiveData.value = UiDataBean.success(userRepository.getCurrentUser())
    }

    fun getUserState() = userStateLiveData

    fun getUserDetail() = userDetailsLiveData
}