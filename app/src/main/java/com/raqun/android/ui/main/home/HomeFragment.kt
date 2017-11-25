package com.raqun.android.ui.main.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.raqun.android.R
import com.raqun.android.databinding.FragmentHomeBinding
import com.raqun.android.extensions.observeApi
import com.raqun.android.model.ProductListType
import com.raqun.android.ui.BinderFragment
import com.raqun.android.extensions.setup
import com.raqun.android.ui.main.FabProvider

/**
 * Created by tyln on 29/07/2017.
 */
class HomeFragment : BinderFragment<FragmentHomeBinding, HomeViewModel>(), HomeView {

    override fun getModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is FabProvider) {
            (activity as FabProvider).showFab()
        }

        viewModel.getTop().observeApi(this, { bean -> binding.topProductsBean = bean })

        viewModel.getRecent().observeApi(this, { bean -> binding.recentProductsBean = bean })

        viewModel.getDiscounted().observeApi(this, { bean -> binding.discountedProductsBean = bean })

        viewModel.getApps().observeApi(this, { bean -> binding.topFollowedAppsBean = bean })
    }

    override fun initView() {
        binding.homeView = this
        binding.navigator = navigationController
        binding.recyclerviewTop?.setup(activity, LinearLayoutManager.HORIZONTAL)
        binding.recyclerviewRecent?.setup(activity, LinearLayoutManager.HORIZONTAL)
        binding.recyclerviewDiscount?.setup(activity, LinearLayoutManager.HORIZONTAL)
        binding.recyclerviewApps?.setup(activity, LinearLayoutManager.HORIZONTAL)
    }

    override fun onMoreTopClicked() {
        navigationController?.navigateToProducts(ProductListType.TOP)
    }

    override fun onMoreRecentClicked() {
        navigationController?.navigateToProducts(ProductListType.RECENT)
    }

    override fun onMoreDiscountClicked() {
        navigationController?.navigateToProducts(ProductListType.DISCOUNT)
    }

    override fun onMoreAppsClicked() {
        //navigationController?.navigateToProducts()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}