package com.raqun.android.ui.main.notifications

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.raqun.android.R
import com.raqun.android.data.DataBean
import com.raqun.android.databinding.FragmentNotificationsBinding
import com.raqun.android.model.Notification
import com.raqun.android.ui.BinderFragment
import com.raqun.android.ui.view.AuthView
import com.raqun.android.extensions.decorate
import com.raqun.android.extensions.observeApi
import com.raqun.android.extensions.setup
import com.raqun.android.ui.main.FabProvider

/**
 * Created by tyln on 31/07/2017.
 */
class NotificationsFragment : BinderFragment<FragmentNotificationsBinding, NotificationsViewModel>(),
        AuthView {

    override fun getLayoutRes(): Int = R.layout.fragment_notifications

    override fun getModelClass(): Class<NotificationsViewModel> = NotificationsViewModel::class.java

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is FabProvider) {
            (activity as FabProvider).hideFab()
        }

        binding.layoutAuth.isUserLoggedIn = viewModel.isUserLoggedIn()
        if (viewModel.isUserLoggedIn()) {
            viewModel.getNotifications().observeApi(this, { bean -> binding.notificationsBean = bean })
        }
    }

    override fun initView() {
        binding.layoutAuth.authView = this
        binding.layoutAuth.message = getString(R.string.information_login_for_notifications)
        binding.navigator = navigationController
        binding.recyclerviewNotifications?.let {
            it.setup(activity, LinearLayoutManager.VERTICAL)
            it.decorate()
        }
    }

    override fun loginButtonClicked() {
        navigationController?.navigateToLogin()
    }

    override fun registerButtonClicked() {
        navigationController?.navigateToRegister()
    }

    override fun getTitleRes() = R.string.screen_title_notifications

    companion object {
        fun newInstance() = NotificationsFragment()
    }
}