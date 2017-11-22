package com.raqun.android.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init

/**
 * Created by tyln on 07/08/2017.
 */
class RegisterActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init(savedInstanceState, RegisterFragment.newInstance())
    }

    companion object {
        fun newIntent(conext: Context) = Intent(conext, RegisterActivity::class.java)
    }
}