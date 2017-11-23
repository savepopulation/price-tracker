package com.raqun.android.ui.product

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.raqun.android.R
import com.raqun.android.databinding.FragmentProductBinding
import com.raqun.android.model.Product
import com.raqun.android.ui.BaseActivity
import com.raqun.android.ui.BinderFragment
import com.raqun.android.ui.NavigationController
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout


/**
 * Created by tyln on 06/09/2017.
 */
class ProductFragment : BinderFragment<FragmentProductBinding, ProductViewModel>() {

    private var product: Product? = null

    private var productId: String? = null

    override fun getModelClass(): Class<ProductViewModel> = ProductViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_product

    //override fun getMenuRes() = R.menu.menu_product

    override fun getTitleRes() = R.string.screen_title_product_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(BUNDLE_ID)
            product = it.getParcelable(BUNDLE_PRODUCT)
            product?.let {
                productId = it.productId
            }
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabsViewPager = view?.findViewById<ViewPager>(R.id.viewpager)?.apply {
            adapter = ProductTabsAdapter(resources.getStringArray(R.array.product_detail_tabs),
                    product, productId, childFragmentManager)
        }

        view?.findViewById<TabLayout>(R.id.tabs)?.apply {
            setupWithViewPager(tabsViewPager)
        }

        if (activity is BaseActivity) {
            (activity as BaseActivity).initNavigation(NavigationController.NavigationType.BACK)
        }

        product?.let {
            binding.product = it
        }
    }

    companion object {
        private const val BUNDLE_PRODUCT = "product"
        private const val BUNDLE_ID = "product_id"

        fun newInstance(product: Product?) = ProductFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_PRODUCT, product)
            arguments = bundle
        }

        fun newInstance(productId: String) = ProductFragment().apply {
            val bundle = Bundle()
            bundle.putString(BUNDLE_ID, productId)
            arguments = bundle
        }
    }
}