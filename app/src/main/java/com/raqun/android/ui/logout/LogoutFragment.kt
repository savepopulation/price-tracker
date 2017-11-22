package com.raqun.android.ui.logout

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.raqun.android.R
import com.raqun.android.databinding.FragmentLogoutBinding
import com.raqun.android.ui.BinderFragment

/**
 * Created by tyln on 23/08/2017.
 */
class LogoutFragment : BinderFragment<FragmentLogoutBinding, LogoutViewModel>() {

    override fun getModelClass(): Class<LogoutViewModel> = LogoutViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_logout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (!viewModel.isUserLoggedIn()) {
            navigationController?.navigateToHome()
            return
        }

        viewModel.getLogoutLiveData().observe(this, Observer { result: Boolean? ->
            if (result == true) {
                alert(getString(R.string.success_message_logout))
            }
            navigationController?.navigateToHome()
        })
    }

    override fun initView() {
        binding.progressbar.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = LogoutFragment()
    }
}