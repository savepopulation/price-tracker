package com.raqun.android.ui.logout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init

/**
 * Created by tyln on 23/08/2017.
 */
class LogoutActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init(savedInstanceState, LogoutFragment.newInstance())
    }

    override fun onBackPressed() {
        // Disabled
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, LogoutActivity::class.java)
    }
}