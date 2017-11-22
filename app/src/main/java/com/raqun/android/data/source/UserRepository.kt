package com.raqun.android.data.source

import com.raqun.android.RaqunApp
import com.raqun.android.session.State
import com.raqun.android.data.source.local.UserLocalDataSource
import com.raqun.android.data.source.remote.UserRemoteDataSource
import com.raqun.android.model.*
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.PagedResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tyln on 07/08/2017.
 */
class UserRepository @Inject constructor(private val userLocalDataSource: UserLocalDataSource,
                                         private val userRemoteDataSource: UserRemoteDataSource) : UserDataSource {

    override fun getUserCredentials(): User? = userLocalDataSource.getUserCredentials()

    override fun saveUser(registerRequest: RegisterRequest): Completable
            = userRemoteDataSource.saveUser(registerRequest)

    override fun login(userName: String, password: String): Single<User> {
        return userRemoteDataSource.login(userName, password)
                .doOnSuccess({ user: User? ->
                    RaqunApp.sessionManager.updateSession(user)
                    user?.let {
                        userLocalDataSource.saveCredantials(user)
                    }
                })
    }

    override fun logout(): Completable = userRemoteDataSource.logout()

    override fun contact(messageType: Int, message: String) {
        userRemoteDataSource.contact(messageType, message)
    }

    override fun getNotifications(page: Page): Single<PagedResponse<Notification>> {
        return userRemoteDataSource.getNotifications(page)
    }

    override fun getFavoriteProducts(page: Page): Single<PagedResponse<Product>> {
        return userRemoteDataSource.getFavoriteProducts(page)
    }

    // Business methods
    fun isUserLoggedIn() = RaqunApp.sessionManager.getState() == State.LOGIN

    fun getCurrentUser() = RaqunApp.sessionManager.getCurrentUser()

    fun dropSession() {
        userLocalDataSource.clearCredentials()
        RaqunApp.sessionManager.dropSession()
    }
}