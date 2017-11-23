package com.raqun.android.ui.register

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.DataBean
import com.raqun.android.databinding.FragmentRegisterBinding
import com.raqun.android.model.UiDataBean
import com.raqun.android.ui.BinderFragment
import com.raqun.android.util.Alert
import com.raqun.android.extensions.init

/**
 * Created by tyln on 07/08/2017.
 */
class RegisterFragment : BinderFragment<FragmentRegisterBinding, RegisterViewModel>(),
        RegisterView {

    private lateinit var registerProgressDialog: ProgressDialog

    override fun getModelClass(): Class<RegisterViewModel> = RegisterViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_register

    override fun getTitleRes() = R.string.screen_title_register

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO try to do with lazy
        // TODO change progress dialog
        registerProgressDialog = ProgressDialog(context).apply {
            setMessage(getString(R.string.dialog_message_wait))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getRegisterLiveData().observe(this, Observer { bean: DataBean<Boolean>? ->
            bean?.run {
                registerProgressDialog.init(getState())
                if (hasError()) {
                    onError(getError())
                } else if (getData() == true) {
                    Alert.create(activity,
                            getString(R.string.dialog_title_register_success),
                            getString(R.string.success_message_register),
                            true,
                            R.string.button_ok,
                            Constants.NO_RES,
                            { navigationController?.close() },
                            {}).show()
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
        if (registerProgressDialog.isShowing) {
            registerProgressDialog.dismiss()
        }
        super.onDestroyView()
    }

    override fun initView() {
        binding.registerView = this
    }

    override fun register() {
        // TODO handle checks with RxBinding
        val isChecked = binding.checkboxAgreement.isChecked
        if (!isChecked) {
            alert(getString(R.string.error_message_user_agreement))
            return
        }

        val minDefaultInputLen = resources.getInteger(R.integer.min_default_input_len)

        val email = binding.edittextEmail.text.toString().trim()
        if (email.length < minDefaultInputLen) {
            alert(getString(R.string.error_message_email))
            return
        }

        val fullName = binding.edittextEmail.text.toString().trim()
        if (fullName.length < minDefaultInputLen) {
            alert(getString(R.string.error_message_full_name))
            return
        }

        val userName = binding.edittextUsername.text.toString().trim()
        if (userName.length < resources.getInteger(R.integer.min_valid_username_len)) {
            alert(getString(R.string.error_message_username))
            return
        }

        val password = binding.edittextPassword.text.toString().trim()
        if (password.length < resources.getInteger(R.integer.min_valid_passwrod_len)) {
            alert(getString(R.string.error_message_password))
            return
        }

        viewModel.register(email, fullName, isChecked, userName, password)
    }

    override fun showUserAgreement() {
        navigationController?.navigateToContent(Constants.CONTENT_TYPE_USER_AGREEMENT,
                getString(R.string.content_title_user_agreement))
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}