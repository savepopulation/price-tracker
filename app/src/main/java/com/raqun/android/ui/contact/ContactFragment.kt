package com.raqun.android.ui.contact

import com.raqun.android.R
import com.raqun.android.databinding.FragmentContactBinding
import com.raqun.android.ui.BaseFragment
import com.raqun.android.ui.BinderFragment

/**
 * Created by tyln on 14/08/2017.
 */
class ContactFragment : BinderFragment<FragmentContactBinding, ContactViewModel>(), ContactView {

    override fun getModelClass() = ContactViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_contact

    override fun getTitleRes() = R.string.screen_title_contact

    override fun initView() {
        binding.contactView = this
    }

    override fun sendMessage() {
        // TODO improve this implementation with rx bindings
        val feedBack = binding.edittextMessage.text.trim().toString()
        if (feedBack.length < resources.getInteger(R.integer.min_feedback_length)) {
            alert(getString(R.string.error_message_content_length))
            return
        }

        viewModel.sendMessage(binding.spinnerContactTypes.selectedItemPosition, feedBack)
        alert(getString(R.string.success_message_send_feedback))
        navigationController?.close()
    }

    companion object {
        fun newInstance() = ContactFragment()
    }
}