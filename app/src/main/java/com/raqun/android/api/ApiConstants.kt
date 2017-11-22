package com.raqun.android.api

/**
 * Created by tyln on 22/07/2017.
 */
class ApiConstants private constructor() {

    companion object {
        val TIMEOUT_INMILIS = 15000L
        val DEFAULT_GRANT_TYPE = "password"

        val ERROR_CODE_AUTH = 401
        val ERROR_CODE_NETWORK = -1
    }
}