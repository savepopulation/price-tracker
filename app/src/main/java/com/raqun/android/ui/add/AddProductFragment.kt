package com.raqun.android.ui.add

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.crashlytics.android.answers.Answers
import com.raqun.android.R
import com.raqun.android.api.ApiConstants
import com.raqun.android.data.DataBean
import com.raqun.android.data.DataState
import com.raqun.android.databinding.FragmentAddBinding
import com.raqun.android.extensions.*
import com.raqun.android.model.UiDataBean
import com.raqun.android.ui.BinderFragment
import com.raqun.android.ui.view.AuthView
import com.raqun.android.util.reportAddProduct

/**
 * Created by tyln on 18/09/2017.
 */
class AddProductFragment : BinderFragment<FragmentAddBinding, AddProductViewModel>(),
        AddProductView, AuthView {

    override fun getModelClass() = AddProductViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_add

    override fun getTitleRes() = R.string.screen_title_add

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.layoutAuth.isUserLoggedIn = viewModel.isUserLoggedIn()
        binding.isUserLoggedIn = viewModel.isUserLoggedIn()

        viewModel.getAddProduct().observe(this, Observer { bean: DataBean<Boolean>? ->
            binding.addProductBean = bean
            if (bean == null || bean.hasError()) {
                if (bean?.getError()?.code == ApiConstants.ERROR_CODE_AUTH) {
                    onError(bean.getError())
                    return@Observer
                }

                binding.coordinator.snackThat(getString(R.string.error_message_add_product),
                        getString(R.string.button_retry),
                        View.OnClickListener {
                            addProduct()
                        })
            } else if (bean.getState() == DataState.SUCCESS) {
                binding.coordinator.snackThat(getString(R.string.success_message_add_product),
                        getString(R.string.button_ok), null)
            }

            if (bean != null && bean.getState() != DataState.FETCHING) {
                Answers.getInstance().reportAddProduct(bean.getState(),
                        binding.edittextUrl.text.toString())
            }
        })

        if (viewModel.isUserLoggedIn()) {
            binding.edittextUrl.pasteFromClipBoard()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_clear -> clearEditText()
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initView() {
        binding.uiCallback = this
        binding.layoutAuth.authView = this
        binding.layoutAuth.message = getString(R.string.information_login_for_add_product)
    }

    override fun addProduct() {
        val url = binding.edittextUrl.getUrl()
        url?.let {
            activity.forceCloseKeyboard()
            viewModel.addProduct(it)
        }
    }

    override fun getMenuRes(): Int = R.menu.menu_add

    override fun loginButtonClicked() {
        navigationController?.navigateToLogin()
    }

    override fun registerButtonClicked() {
        navigationController?.navigateToRegister()
    }

    private fun clearEditText(): Boolean {
        binding.edittextUrl.clear()
        return true
    }

    companion object {
        fun newInstance() = AddProductFragment()
    }
}