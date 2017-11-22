package com.raqun.android.data.source

import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.model.Alarm
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tyln on 29/07/2017.
 */
interface ProductDataSource {
    fun getRecentFollowedProducts(page: Page): Single<PagedResponse<Product>>

    fun getTopFollowedProducts(page: Page): Single<PagedResponse<Product>>

    fun getDiscountedProducts(page: Page): Single<PagedResponse<Product>>

    fun getTopWebApps(page: Page): Single<PagedResponse<WebApp>>

    fun addProduct(addProductRequest: AddProductRequest): Completable

    fun getAlarms(alarmRequest: AlarmRequest): Single<PagedResponse<Alarm>>
}