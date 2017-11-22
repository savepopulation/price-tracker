package com.raqun.android.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

/**
 * Created by tyln on 27/07/2017.
 */
abstract class BinderFragment<VB : ViewDataBinding, VM : ViewModel> : BaseFragment() {

    @Inject protected lateinit var vmFactory: ViewModelProvider.Factory
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    abstract fun getModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, vmFactory).get(getModelClass())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        initView()
        return binding.root
    }

    open protected fun initView() {
        // Can be overridden from subclasses
    }
}