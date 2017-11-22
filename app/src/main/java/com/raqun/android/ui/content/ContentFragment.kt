package com.raqun.android.ui.content

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import com.raqun.android.R
import com.raqun.android.databinding.FragmentContentBinding
import com.raqun.android.model.Content
import com.raqun.android.model.UiDataBean
import com.raqun.android.ui.BinderFragment

/**
 * Created by tyln on 26/08/2017.
 */
class ContentFragment : BinderFragment<FragmentContentBinding, ContentViewModel>() {

    private var contentId: Int = 0

    override fun getModelClass(): Class<ContentViewModel> = ContentViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contentId = it.getInt(BUNDLE_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getContentLiveData().observe(this, Observer {
            bean: UiDataBean<Content>? ->
            bean?.let {
                if (bean.hasError()) {
                    onError(bean.getError())
                }
                binding.setContent(bean.getData())
            }
        })
        viewModel.setContentId(contentId)
    }

    companion object {
        private const val BUNDLE_ID = "content_id"

        fun newInstance(contentId: Int): ContentFragment = ContentFragment().apply {
            val bundle = Bundle()
            bundle.putInt(BUNDLE_ID, contentId)
            arguments = bundle
        }
    }
}