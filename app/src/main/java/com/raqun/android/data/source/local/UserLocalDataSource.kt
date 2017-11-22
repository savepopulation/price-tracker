package com.raqun.android.data.source.local

import android.content.Context
import com.raqun.android.data.source.UserDataSource
import com.raqun.android.model.User
import com.raqun.android.api.request.RegisterRequest
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import com.raqun.android.model.Notification
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.api.response.PagedResponse


/**
 * Created by tyln on 07/08/2017.
 */
class UserLocalDataSource @Inject constructor(private val context: Context) : UserDataSource {

    override fun getUserCredentials(): User? = UserHelper.getUserCredentials(context)

    override fun saveUser(registerRequest: RegisterRequest): Completable = Completable.complete()

    override fun login(userName: String, password: String): Single<User> {
        return Single.never()
    }

    override fun logout(): Completable {
        return Completable.create {
            UserHelper.clearCredentials(context)
        }
    }

    override fun contact(messageType: Int, message: String) {
        // Not implemented..
    }

    override fun getNotifications(page: Page): Single<PagedResponse<Notification>> = Single.never()

    override fun getFavoriteProducts(page: Page): Single<PagedResponse<Product>> = Single.never()

    fun saveCredantials(user: User?) {
        UserHelper.saveUserCredentials(context, user)
    }

    fun clearCredentials() {
        UserHelper.clearCredentials(context)
    }
}