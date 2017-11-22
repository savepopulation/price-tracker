package com.raqun.android.ui.product.detail

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.DataBean
import com.raqun.android.databinding.FragmentDetailsBinding
import com.raqun.android.extensions.observeApi
import com.raqun.android.model.Product
import com.raqun.android.ui.BaseActivity
import com.raqun.android.ui.BinderFragment
import com.raqun.android.ui.product.ProductFragment

/**
 * Created by tyln on 06/11/2017.
 */
class DetailFragment : BinderFragment<FragmentDetailsBinding, DetailViewModel>() {

    private var product: Product? = null

    private var productId: String? = null

    override fun getModelClass() = DetailViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            product = it.getParcelable(BUNDLE_PRODUCT)
            productId = it.getString(BUNDLE_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        product?.let {
            if (activity is BaseActivity) {
                (activity as BaseActivity).setScreenTitle(it.name)
            }
        }

        viewModel.getProduct().observeApi(this, { bean -> binding.product = bean?.getData() })

        viewModel.setProduct(product)
        viewModel.setProductId(productId)
    }

    override fun getTitleRes() = Constants.NO_RES

    companion object {
        private const val BUNDLE_PRODUCT = "product"
        private const val BUNDLE_ID = "product_id"

        fun newInstance(product: Product?, productId: String?) = DetailFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_PRODUCT, product)
            bundle.putString(BUNDLE_ID, productId)
            arguments = bundle
        }
    }
}