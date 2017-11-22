package com.raqun.android.ui.login

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.raqun.android.BuildConfig
import com.raqun.android.R
import com.raqun.android.databinding.FragmentLoginBinding
import com.raqun.android.model.UiDataBean
import com.raqun.android.model.User
import com.raqun.android.ui.BinderFragment
import com.raqun.android.extensions.init

/**
 * Created by tyln on 07/08/2017.
 */


class LoginFragment : BinderFragment<FragmentLoginBinding, LoginViewModel>(), LoginView {

    private lateinit var loginProgressDialog: ProgressDialog

    override fun getModelClass(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_login

    override fun getTitleRes() = R.string.screen_title_login

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO try to do with lazy
        // TODO change progress dialog
        loginProgressDialog = ProgressDialog(context).apply {
            setMessage(getString(R.string.dialog_message_logging_in))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getLoginLiveData().observe(this, Observer { bean: UiDataBean<User>? ->
            bean?.let {
                loginProgressDialog.init(bean.getState())
                if (bean.hasError()) {
                    onError(bean.getError())
                } else {
                    bean.getData()?.let {
                        alert(getString(R.string.success_message_login, bean.getData()!!.userName))
                        navigationController?.navigateToHome()
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isUserLoggedIn()) {
            navigationController?.close()
        }
    }

    override fun onDestroyView() {
        if (loginProgressDialog.isShowing) {
            loginProgressDialog.dismiss()
        }
        super.onDestroyView()
    }

    override fun initView() {
        binding.loginView = this
        if (BuildConfig.DEBUG) {
            binding.buttonLogin.setOnLongClickListener {
                binding.edittextUsername.setText("mute")
                binding.edittextPassword.setText("Mk1907")
                true
            }
        }
    }

    override fun login() {
        // TODO improve with rx binding
        val userName = binding.edittextUsername.text.toString().trim()
        val password = binding.edittextPassword.text.toString().trim()

        if (userName.length < resources.getInteger(R.integer.min_valid_username_len)) {
            alert(getString(R.string.error_message_username))
            return
        }

        if (password.length < resources.getInteger(R.integer.min_valid_passwrod_len)) {
            alert(getString(R.string.error_message_password))
            return
        }

        viewModel.login(userName, password)
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}