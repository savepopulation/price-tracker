package com.raqun.android.data.source.remote

import com.raqun.android.api.RaqunServices
import com.raqun.android.data.source.ProductDataSource
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.WebApp
import com.raqun.android.api.request.AddProductRequest
import com.raqun.android.api.request.AlarmRequest
import com.raqun.android.api.response.DefaultResponse
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.model.Alarm
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tyln on 29/07/2017.
 */
class ProductRemoteDataSource @Inject constructor(private val raqunServices: RaqunServices)
    : ProductDataSource {

    override fun getTopFollowedProducts(page: Page): Single<PagedResponse<Product>> =
            raqunServices.getTopFollowedProducts(page)
                    .map { response: DefaultResponse<PagedResponse<Product>> ->
                        response.data
                    }


    override fun getDiscountedProducts(page: Page): Single<PagedResponse<Product>> =
            raqunServices.getDiscountedProducts(page)
                    .map { response: DefaultResponse<PagedResponse<Product>> ->
                        response.data
                    }


    override fun getRecentFollowedProducts(page: Page): Single<PagedResponse<Product>> =
            raqunServices.getRecentFollowedProducts(page)
                    .map { response: DefaultResponse<PagedResponse<Product>> ->
                        response.data
                    }

    override fun getTopWebApps(page: Page): Single<PagedResponse<WebApp>> =
            raqunServices.getTopWebApps(page)
                    .map { response: DefaultResponse<PagedResponse<WebApp>> ->
                        response.data
                    }

    override fun addProduct(addProductRequest: AddProductRequest): Completable =
            raqunServices.addProduct(addProductRequest)

    override fun getAlarms(alarmRequest: AlarmRequest): Single<PagedResponse<Alarm>> =
            raqunServices.getAlarms(alarmRequest)
                    .map { response: DefaultResponse<PagedResponse<Alarm>> ->
                        response.data
                    }
}