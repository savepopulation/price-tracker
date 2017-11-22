package com.raqun.android.data.source

import com.raqun.android.model.Content
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by tyln on 24/08/2017.
 */
interface ResourceDataSource {
    fun getContent(contentId: Int): Single<Content>
}