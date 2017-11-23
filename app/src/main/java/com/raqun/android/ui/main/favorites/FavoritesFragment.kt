package com.raqun.android.ui.main.favorites

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.RaqunApp
import com.raqun.android.databinding.FragmentFavoritesBinding
import com.raqun.android.extensions.observeApi
import com.raqun.android.model.Product
import com.raqun.android.model.UiDataBean
import com.raqun.android.ui.BinderFragment
import com.raqun.android.ui.main.FabProvider
import com.raqun.android.ui.view.AuthView

/**
 * Created by tyln on 31/07/2017.
 */
class FavoritesFragment : BinderFragment<FragmentFavoritesBinding, FavoritesViewModel>(),
        AuthView {

    override fun getLayoutRes() = R.layout.fragment_favorites

    override fun getTitleRes() = R.string.screen_title_favorites

    override fun getModelClass(): Class<FavoritesViewModel> = FavoritesViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is FabProvider) {
            (activity as FabProvider).showFab()
        }

        binding.layoutAuth.isUserLoggedIn = viewModel.isUserLoggedIn()

        if (viewModel.isUserLoggedIn()) {
            viewModel.getProducts().observeApi(this, { bean -> binding.productsBean = bean })
        }
    }

    override fun initView() {
        binding.navigator = navigationController
        binding.layoutAuth.authView = this
        binding.layoutAuth.message = getString(R.string.information_login_for_favorites)

        binding.recyclerviewProducts?.apply {
            layoutManager = GridLayoutManager(activity, Constants.DEFAULT_GRID_COLUMN_COUNT)
            setHasFixedSize(true)
        }
    }

    override fun loginButtonClicked() {
        navigationController?.navigateToLogin()
    }

    override fun registerButtonClicked() {
        navigationController?.navigateToRegister()
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}