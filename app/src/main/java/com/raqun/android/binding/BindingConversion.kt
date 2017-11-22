package com.raqun.android.binding

import android.databinding.BindingConversion
import android.view.View
import com.raqun.android.data.DataBean
import com.raqun.android.data.DataState

/**
 * Created by tyln on 03/08/2017.
 */
object BindingConversion {

    @JvmStatic
    @BindingConversion
    fun <T> bindBeanToProgress(bean: DataBean<T>?): Int =
            if (bean?.getState() == DataState.FETCHING && bean.getData() == null) View.VISIBLE else View.GONE

    @JvmStatic
    @BindingConversion
    fun bindBooleanToVisiblity(isVisible: Boolean): Int
            = if (isVisible) View.VISIBLE else View.GONE

}