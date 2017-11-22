package com.raqun.android.api

import com.raqun.android.model.*
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.request.RegisterTokenRequest
import com.raqun.android.api.request.RegisterRequest
import com.raqun.android.api.response.DefaultResponse
import com.raqun.android.api.response.PagedResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded


/**
 * Created by tyln on 22/07/2017.
 */
interface RaqunServices {

    // USER END POINTS
    @FormUrlEncoded
    @POST("token")
    fun auth(@Field("username") username: String,
             @Field("password") password: String,
             @Field("grant_type") grantType: String): Single<User>

    @POST("Register")
    fun registerUser(@Body registerRequest: RegisterRequest): Completable

    @POST("Logout")
    fun logout(): Completable

    @POST("Notification")
    fun getNotifications(@Body page: Page): Single<DefaultResponse<PagedResponse<Notification>>>

    @POST("SaveDevice")
    fun registerToken(@Body registerTokenRequest: RegisterTokenRequest): Completable

    // PRODUCT ENDPOINTS
    @POST("Featured/RecentFollowedProducts")
    fun getRecentFollowedProducts(@Body page: Page): Single<DefaultResponse<PagedResponse<Product>>>

    @POST("Featured/TopFollowedProducts")
    fun getTopFollowedProducts(@Body page: Page): Single<DefaultResponse<PagedResponse<Product>>>

    @POST("Featured/DiscountedProducts")
    fun getDiscountedProducts(@Body page: Page): Single<DefaultResponse<PagedResponse<Product>>>

    @POST("WebSite")
    fun getTopWebApps(@Body page: Page): Single<DefaultResponse<PagedResponse<WebApp>>>

    @POST("UserProduct")
    fun getFavoriteProducts(@Body page: Page): Single<DefaultResponse<PagedResponse<Product>>>

    @POST("UserProduct/Add")
    fun addProduct(@Body addProductRequest: AddProductRequest): Completable

    @POST("Alarm")
    fun getAlarms(@Body alarmRequest: AlarmRequest): Single<DefaultResponse<PagedResponse<Alarm>>>

}