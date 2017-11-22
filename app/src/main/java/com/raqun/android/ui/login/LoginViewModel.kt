package com.raqun.android.ui.login

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.Error
import com.raqun.android.data.source.UserRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 07/08/2017.
 */
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val loginLiveData = MutableLiveData<UiDataBean<User>>()

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    fun login(userName: String, password: String) {
        loginLiveData.postValue(UiDataBean.fetching(null))
        userRepository.login(userName, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onSuccess = {
                            loginLiveData.postValue(UiDataBean.success(it))
                        },
                        onError = {
                            loginLiveData.postValue(UiDataBean.error(null, it.getError()))
                        }
                )
    }

    fun getLoginLiveData() = loginLiveData;
}