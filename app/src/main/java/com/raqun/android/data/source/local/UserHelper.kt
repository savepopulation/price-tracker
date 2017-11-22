package com.raqun.android.data.source.local

import android.content.Context
import com.google.gson.Gson
import com.raqun.android.model.User
import com.raqun.android.util.Encrypt
import com.raqun.android.util.SharedPrefUtil

/**
 * Created by tyln on 21/09/2017.
 */
class UserHelper {

    companion object {
        private val MEMBER_KEY = "aa08769cdcb26674c6706093503ff0a3"
        private val HASH_KEY = "hash_goes_here"
        private val SALT = "salt_goes_here"
        private val DEFAULT_CHARSET = "UTF-8"

        fun clearCredentials(context: Context) {
            SharedPrefUtil.remove(context, MEMBER_KEY)
        }

        fun saveUserCredentials(context: Context, user: User?) {
            user?.let {
                try {
                    val credentials = Gson().toJson(user)
                    val encryptedCredentials = Encrypt.crypto(credentials,
                            Encrypt.CRYPTO_TYPE_ENCRYPT,
                            HASH_KEY, SALT,
                            DEFAULT_CHARSET)
                    SharedPrefUtil.put(context, MEMBER_KEY, encryptedCredentials)
                } catch (e: Exception) {
                    // ignored
                }
            }
        }

        fun getUserCredentials(context: Context): User? {
            var user: User? = null
            try {
                val userCredentials = SharedPrefUtil.get(context, MEMBER_KEY, "")
                val deCryptedCredentials = Encrypt.crypto(userCredentials,
                        Encrypt.CRYPTO_TYPE_DECRYPT, HASH_KEY, SALT, DEFAULT_CHARSET)
                user = Gson().fromJson<User>(deCryptedCredentials, User::class.java)
            } catch (e: Exception) {
                // ignored
            }

            return user
        }
    }
}