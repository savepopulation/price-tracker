package com.raqun.android.data.source

import com.raqun.android.model.Notification
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.User
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.PagedResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tyln on 07/08/2017.
 */
interface UserDataSource {

    fun getUserCredentials(): User?

    fun saveUser(registerRequest: RegisterRequest): Completable

    fun login(userName: String, password: String): Single<User>

    fun logout(): Completable

    fun contact(messageType: Int, message: String)

    fun getNotifications(page: Page): Single<PagedResponse<Notification>>

    fun getFavoriteProducts(page: Page): Single<PagedResponse<Product>>
}