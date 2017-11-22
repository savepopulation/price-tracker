package com.raqun.android.model

import com.raqun.android.data.DataBean
import com.raqun.android.data.DataState
import com.raqun.android.data.Error

/**
 * Created by tyln on 31/07/2017.
 */
class UiDataBean<T> private constructor(private val dataState: DataState,
                                        private val beanData: T?,
                                        private val error: Error?) : DataBean<T> {

    companion object {
        fun <T> success(data: T?) = UiDataBean(DataState.SUCCESS, data, null)

        fun <T> error(data: T?, error: Error?) = UiDataBean(DataState.ERROR, data, error)

        fun <T> fetching(data: T?) = UiDataBean(DataState.FETCHING, data, null)
    }

    override fun getData(): T? = beanData

    override fun getState(): DataState = dataState

    override fun hasError(): Boolean = error != null

    override fun getError(): Error? = error
}