package com.raqun.android.api

/**
 * Created by tyln on 22/07/2017.
 */
class ApiConstants private constructor() {

    companion object {
        const val TIMEOUT_INMILIS = 15000L
        const val DEFAULT_GRANT_TYPE = "password"

        // SPECIFIC ERROR CODES
        const val ERROR_CODE_AUTH = 401
        const val ERROR_CODE_NETWORK = -1
    }
}