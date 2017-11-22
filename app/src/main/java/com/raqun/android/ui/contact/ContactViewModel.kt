package com.raqun.android.ui.contact

import android.arch.lifecycle.ViewModel
import com.raqun.android.data.source.UserRepository
import javax.inject.Inject

/**
 * Created by tyln on 14/08/2017.
 */
class ContactViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun sendMessage(messageType: Int, message: String) {
        userRepository.contact(messageType, message)
    }
}