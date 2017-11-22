package com.raqun.android.ui.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.model.ProductListType
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init

/**
 * Created by tyln on 02/10/2017.
 */
class ProductsActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var productType = ProductListType.FAV

        intent.extras?.let {
            productType = ProductListType.values()[it.getInt(BUNDLE_TYPE)]
        }

        init(savedInstanceState, ProductsFragment.newInstance(productType))
    }

    companion object {
        private const val BUNDLE_TYPE = "type"

        fun newIntent(context: Context, productListType: ProductListType) =
                Intent(context, ProductsActivity::class.java).apply {
                    putExtra(BUNDLE_TYPE, productListType.ordinal)
                }
    }
}