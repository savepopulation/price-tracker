package com.raqun.android.data

/**
 * Created by tyln on 31/07/2017.
 */
interface DataBean<out T> {
    fun getData(): T?

    fun getState(): DataState

    fun hasError(): Boolean

    fun getError(): Error?
}