package com.raqun.android.ui.contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init

/**
 * Created by tyln on 14/08/2017.
 */
class ContactActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init(savedInstanceState, ContactFragment.newInstance())
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, ContactActivity::class.java)
    }
}