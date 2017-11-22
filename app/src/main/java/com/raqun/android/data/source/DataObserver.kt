package com.raqun.android.data.source

import android.arch.lifecycle.*
import com.raqun.android.data.DataBean
import com.raqun.android.ui.BaseView
import java.lang.ref.WeakReference
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleObserver

/**
 * Created by tyln on 16/11/2017.
 */
class DataObserver<T : DataBean<T>>(owner: LifecycleRegistry,
                                    view: BaseView,
                                    private var observer: Observer<DataBean<T>>?)
    : Observer<DataBean<T>>, LifecycleObserver {

    private var view: WeakReference<BaseView>? = null

    init {
        this.view = WeakReference(view)
        owner.addObserver(this)
    }

    override fun onChanged(t: DataBean<T>?) {
        if (view!!.get() == null) return

        if (t != null && t.hasError()) {
            view!!.get()!!.onError(t.getError())
        }

        observer!!.onChanged(t)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        this.view = null
        this.observer = null
    }
}