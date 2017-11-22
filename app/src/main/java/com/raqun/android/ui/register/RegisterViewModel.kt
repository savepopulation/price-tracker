package com.raqun.android.ui.register

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.UiDataBean
import com.raqun.android.api.request.RegisterRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 07/08/2017.
 */
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val registerLiveData = MutableLiveData<UiDataBean<Boolean>>()

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun getRegisterLiveData() = registerLiveData

    fun register(email: String, fullName: String, contactPermission: Boolean,
                 userName: String, password: String) {

        val registerRequest = RegisterRequest(email,
                fullName,
                contactPermission,
                userName,
                password)

        registerLiveData.postValue(UiDataBean.fetching(null))
        userRepository.saveUser(registerRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onComplete = {
                            registerLiveData.value = UiDataBean.success(true)
                        },
                        onError = {
                            registerLiveData.value = UiDataBean.error(false, it.getError())
                        }
                )
    }
}