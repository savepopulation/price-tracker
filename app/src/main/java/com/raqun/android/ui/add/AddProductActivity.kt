package com.raqun.android.ui.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.raqun.android.R
import com.raqun.android.ui.BaseActivity
import com.raqun.android.extensions.init

/**
 * Created by tyln on 18/09/2017.
 */
class AddProductActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState, AddProductFragment.newInstance())
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AddProductActivity::class.java)
    }
}