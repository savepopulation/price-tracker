package com.raqun.android.ui.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init

/**
 * Created by tyln on 26/08/2017.
 */
class ContentActivity : BaseActivity() {

    private var contentId: Int = 0

    override fun getLayoutRes(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.let {
            contentId = it.getInt(BUNDLE_ID, -1)
            it.getString(BUNDLE_TITLE)?.let {
                supportActionBar?.title = it
            }
        }

        init(savedInstanceState, ContentFragment.newInstance(contentId))
    }

    companion object {
        private const val BUNDLE_ID = "content_id"
        private const val BUNDLE_TITLE = "title"

        fun newIntent(context: Context, contentId: Int, title: String?): Intent {
            return Intent(context, ContentActivity::class.java).apply {
                putExtra(BUNDLE_ID, contentId)
                putExtra(BUNDLE_TITLE, title)
            }
        }
    }
}