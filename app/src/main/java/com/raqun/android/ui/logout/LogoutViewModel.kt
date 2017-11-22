package com.raqun.android.ui.logout

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import com.raqun.android.data.source.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 23/08/2017.
 */
class LogoutViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val logoutLiveData = MutableLiveData<Boolean>()

    init {
        userRepository.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onComplete = { logout() },
                        onError = { logout() }
                )
    }

    fun getLogoutLiveData() = logoutLiveData

    fun isUserLoggedIn() = userRepository.isUserLoggedIn()

    private fun logout() {
        userRepository.dropSession()
        logoutLiveData.postValue(true)
    }
}