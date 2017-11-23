package com.raqun.android

/**
 * Created by tyln on 22/07/2017.
 */
class Constants private constructor() {

    companion object {
        const val NO_RES = 0

        // CONTENT TYPES
        const val CONTENT_TYPE_ABOUT = 1
        const val CONTENT_TYPE_USER_AGREEMENT = 2
        const val CONTENT_TYPE_CONTACT_PERMISSION = 3

        // DATE FORMATS
        const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
        const val NOTIFICATION_DATE_FORMAT = "dd MMMM, HH:mm"
        const val PRODUCT_UPDATE_DATE_FORMAT = "dd MMM yyyy, HH:mm"

        const val DEFAULT_GRID_COLUMN_COUNT = 3
    }
}