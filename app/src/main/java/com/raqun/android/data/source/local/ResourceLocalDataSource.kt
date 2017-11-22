package com.raqun.android.data.source.local

import android.content.Context
import com.raqun.android.Constants
import com.raqun.android.R
import com.raqun.android.data.source.ResourceDataSource
import com.raqun.android.model.Content
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import javax.inject.Inject

/**
 * Created by tyln on 24/08/2017.
 */
class ResourceLocalDataSource @Inject constructor(private val context: Context) : ResourceDataSource {

    override fun getContent(contentId: Int): Single<Content> {
        return Single.create({ e: SingleEmitter<Content> ->
            try {
                val content: Content
                when (contentId) {
                    Constants.CONTENT_TYPE_ABOUT -> content = Content(Constants.CONTENT_TYPE_ABOUT, context.getString(R.string.content_title_about), context.getString(R.string.content_about))
                    Constants.CONTENT_TYPE_USER_AGREEMENT -> content = Content(Constants.CONTENT_TYPE_USER_AGREEMENT, context.getString(R.string.content_title_user_agreement), context.getString(R.string.content_user_agreement))
                    Constants.CONTENT_TYPE_CONTACT_PERMISSION -> content = Content(Constants.CONTENT_TYPE_CONTACT_PERMISSION, context.getString(R.string.content_contact_permissions), context.getString(R.string.content_contact_permissions))
                    else -> content = Content(-1, context.getString(R.string.app_name), context.getString(R.string.content_empty))
                }
                e.onSuccess(content)
            } catch (t: Throwable) {
                e.onError(t)
            }
        })
    }
}