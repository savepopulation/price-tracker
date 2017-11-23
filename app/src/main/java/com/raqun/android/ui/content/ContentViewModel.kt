package com.raqun.android.ui.content

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raqun.android.data.DataBean
import com.raqun.android.data.Error
import com.raqun.android.data.source.ResourceRepository
import com.raqun.android.extensions.getError
import com.raqun.android.model.Content
import com.raqun.android.model.UiDataBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by tyln on 26/08/2017.
 */
class ContentViewModel @Inject constructor(private val resourceRepository: ResourceRepository)
    : ViewModel() {

    private val contentLiveData = MutableLiveData<DataBean<Content>>()

    private var contentId = -1

    fun getContentLiveData() = contentLiveData

    fun setContentId(contentId: Int) {
        if (this.contentId == contentId) {
            return
        }

        this.contentId = contentId
        resourceRepository.getContent(this.contentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            contentLiveData.value = UiDataBean.success(it)
                        },
                        onError = { UiDataBean.error(null, it.getError()) }
                )
    }
}