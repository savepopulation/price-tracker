package com.raqun.android.ui.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init
import com.raqun.android.model.Product

/**
 * Created by tyln on 06/09/2017.
 */
class ProductActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var product: Product? = null
        var productId: String? = null

        intent.extras?.let {
            product = it.getParcelable(BUNDLE_PRODUCT)
            productId = it.getString(BUNDLE_PRODUCT_ID)
        }

        product?.let {
            init(savedInstanceState, ProductFragment.newInstance(it))
            return
        }

        productId?.let {
            init(savedInstanceState, ProductFragment.newInstance(it))
            return
        }

        finish()
    }

    companion object {
        private const val BUNDLE_PRODUCT = "product"
        private const val BUNDLE_PRODUCT_ID = "product_id"

        fun newIntent(context: Context, product: Product?): Intent {
            return Intent(context, ProductActivity::class.java).apply {
                putExtra(BUNDLE_PRODUCT, product)
            }
        }

        fun newIntent(context: Context, productId: String?): Intent {
            return Intent(context, ProductActivity::class.java).apply {
                putExtra(BUNDLE_PRODUCT_ID, productId)
            }
        }
    }
}