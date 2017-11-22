package com.raqun.android.data.source

import com.raqun.android.data.source.remote.ProductRemoteDataSource
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.model.Alarm
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tyln on 29/07/2017.
 */
class ProductRepository @Inject constructor(private val productRemoteDataSource: ProductRemoteDataSource)
    : ProductDataSource {

    override fun getTopFollowedProducts(page: Page): Single<PagedResponse<Product>>
            = productRemoteDataSource.getTopFollowedProducts(page)

    override fun getDiscountedProducts(page: Page): Single<PagedResponse<Product>>
            = productRemoteDataSource.getDiscountedProducts(page)

    override fun getRecentFollowedProducts(page: Page): Single<PagedResponse<Product>>
            = productRemoteDataSource.getRecentFollowedProducts(page)

    override fun getTopWebApps(page: Page): Single<PagedResponse<WebApp>>
            = productRemoteDataSource.getTopWebApps(page)

    override fun addProduct(addProductRequest: AddProductRequest): Completable
            = productRemoteDataSource.addProduct(addProductRequest)

    override fun getAlarms(alarmRequest: AlarmRequest): Single<PagedResponse<Alarm>>
            = productRemoteDataSource.getAlarms(alarmRequest)
}