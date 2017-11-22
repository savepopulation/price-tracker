package com.raqun.android

/**
 * Created by tyln on 22/07/2017.
 */
class Constants private constructor() {

    companion object {
        val NO_RES = 0

        // CONTENT TYPES
        val CONTENT_TYPE_ABOUT = 1
        val CONTENT_TYPE_USER_AGREEMENT = 2
        val CONTENT_TYPE_CONTACT_PERMISSION = 3

        // DATE FORMATS
        val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        val NOTIFICATION_DATE_FORMAT = "dd MMMM, HH:mm"
        val PRODUCT_UPDATE_DATE_FORMAT = "dd MMM yyyy, HH:mm"

        val DEFAULT_GRID_COLUMN_COUNT = 3
    }
}