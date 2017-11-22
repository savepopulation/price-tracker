package com.raqun.android.ui.product.alarms

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.DataBean
import com.raqun.android.databinding.FragmentAlarmsBinding
import com.raqun.android.extensions.decorate
import com.raqun.android.extensions.observeApi
import com.raqun.android.extensions.setup
import com.raqun.android.model.Alarm
import com.raqun.android.ui.BinderFragment

/**
 * Created by tyln on 06/11/2017.
 */
class AlarmsFragment : BinderFragment<FragmentAlarmsBinding, AlarmsViewModel>() {

    private var productId: String? = null

    override fun getModelClass() = AlarmsViewModel::class.java

    override fun getLayoutRes() = R.layout.fragment_alarms

    override fun getTitleRes() = Constants.NO_RES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(BUNDLE_PRODUCT_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getAlarms().observeApi(this, { bean -> binding.alarmsBean = bean })
        viewModel.setProductId(productId)
    }

    override fun initView() {
        binding.alarms.apply {
            setup(activity)
            decorate()
        }
    }

    companion object {
        private const val BUNDLE_PRODUCT_ID = "product_id"

        fun newInstance(productId: String?) = AlarmsFragment().apply {
            val args = Bundle()
            args.putString(BUNDLE_PRODUCT_ID, productId)
            arguments = args
        }
    }
}