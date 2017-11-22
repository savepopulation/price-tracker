package com.raqun.android.data.source.remote

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.raqun.android.api.ApiConstants
import com.raqun.android.api.RaqunServices
import com.raqun.android.data.source.UserDataSource
import com.raqun.android.model.*
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.DefaultResponse
import com.raqun.android.api.response.PagedResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tyln on 07/08/2017.
 */
class UserRemoteDataSource @Inject constructor(private val raqunServices: RaqunServices) : UserDataSource {

    override fun getUserCredentials(): User? = null

    override fun saveUser(registerRequest: RegisterRequest): Completable
            = raqunServices.registerUser(registerRequest)

    override fun login(userName: String, password: String): Single<User>
            = raqunServices.auth(userName, password, ApiConstants.DEFAULT_GRANT_TYPE)

    override fun logout(): Completable = raqunServices.logout()

    override fun contact(messageType: Int, message: String) {
        val contactEvent = CustomEvent("contact").apply {
            putCustomAttribute("type", messageType)
            putCustomAttribute("message", message)
        }

        Answers.getInstance().logCustom(contactEvent)
    }

    override fun getNotifications(page: Page): Single<PagedResponse<Notification>>
            = raqunServices.getNotifications(page)
            .map { response: DefaultResponse<PagedResponse<Notification>> ->
                response.data
            }


    override fun getFavoriteProducts(page: Page): Single<PagedResponse<Product>>
            = raqunServices.getFavoriteProducts(page)
            .map { response: DefaultResponse<PagedResponse<Product>> ->
                response.data
            }
}