package com.raqun.android.ui.products

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.widget.GridLayoutManager
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.databinding.FragmentProductsBinding
import com.raqun.android.extensions.observeApi
import com.raqun.android.model.Product
import com.raqun.android.model.ProductListType
import com.raqun.android.model.UiDataBean
import com.raqun.android.ui.BinderFragment

/**
 * Created by tyln on 02/10/2017.
 */
class ProductsFragment : BinderFragment<FragmentProductsBinding, ProductsViewModel>() {

    private var productType = ProductListType.FAV

    override fun getModelClass() = ProductsViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_products

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productType = ProductListType.values()[it.getInt(BUNDLE_TYPE)]
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setActivityTitle(getString(getTitleFromProductListType(productType)))

        viewModel.getProducts().observeApi(this, { bean -> binding.productsBean = bean })
        viewModel.setProductType(productType)
    }

    override fun initView() {
        binding.navigator = navigationController
        binding.recyclerviewProducts?.apply {
            layoutManager = GridLayoutManager(activity, Constants.DEFAULT_GRID_COLUMN_COUNT)
            setHasFixedSize(true)
        }
    }

    companion object {
        private const val BUNDLE_TYPE = "type"

        fun newInstance(productType: ProductListType) = ProductsFragment().apply {
            val args = Bundle()
            args.putInt(BUNDLE_TYPE, productType.ordinal)
            arguments = args
        }

        @StringRes
        fun getTitleFromProductListType(productListType: ProductListType): Int {
            return when (productListType) {
                ProductListType.TOP -> R.string.label_top_followed_products
                ProductListType.RECENT -> R.string.label_recent_followed_products
                ProductListType.DISCOUNT -> R.string.label_discounted_products
                else -> {
                    R.string.app_name
                }
            }
        }
    }
}