package com.raqun.android.ui.products

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.source.ProductRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Page
import com.raqun.android.model.Product
import com.raqun.android.model.ProductListType
import com.raqun.android.model.UiDataBean
import com.raqun.android.api.response.PagedResponse
import com.raqun.android.data.DataBean
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 02/10/2017.
 */
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private var productType: ProductListType? = null
    private val products = MediatorLiveData<DataBean<List<Product>>>()

    fun getProducts() = products

    fun setProductType(productListType: ProductListType) {

        if (productType != null) {
            return
        }

        this.productType = productListType

        products.value = UiDataBean.fetching(null)
        getProductsObservable()?.let {
            it.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeBy(
                            onSuccess = {
                                products.value = UiDataBean.success(it.items)
                            },

                            onError = {
                                products.value = UiDataBean.error(null, it.getError())
                            }
                    )
        }
    }

    private fun getProductsObservable(): Single<PagedResponse<Product>>? {
        val page = Page(0, 15)

        productType?.let {
            return when (productType) {
                ProductListType.TOP -> productRepository.getTopFollowedProducts(page)
                ProductListType.RECENT -> productRepository.getRecentFollowedProducts(page)
                ProductListType.DISCOUNT -> productRepository.getDiscountedProducts(page)
                else -> {
                    null
                }
            }
        }

        return null
    }
}