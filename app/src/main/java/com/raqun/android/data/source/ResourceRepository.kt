package com.raqun.android.data.source

import com.raqun.android.data.source.local.ResourceLocalDataSource
import com.raqun.android.model.Content
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by tyln on 24/08/2017.
 */
class ResourceRepository @Inject constructor(private val resourceLocalDataSource: ResourceLocalDataSource)
    : ResourceDataSource {

    override fun getContent(contentId: Int): Single<Content>
            = resourceLocalDataSource.getContent(contentId)

}