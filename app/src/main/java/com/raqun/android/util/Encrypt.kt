package com.raqun.android.util

/**
 * Created by tyln on 09/08/2017.
 */

import android.util.Base64

import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec

import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


/**
 * Created by tyln on 10.05.16.
 */
object Encrypt {

    private val CHIPHER_TRANSFORMATION = "AES/ECB/PKCS5Padding"
    private val GENERATE_KEY__ALGORITHM = "PBKDF2WithHmacSHA1"
    private val GENERATE_KEY_ALGORITHM = "AES"
    val CRYPTO_TYPE_ENCRYPT = 0
    val CRYPTO_TYPE_DECRYPT = 1

    fun crypto(inString: String?, type: Int, hashKey: String, salt: String, charset: String): String? {
        var cipher: Cipher? = null
        try {
            cipher = Cipher.getInstance(CHIPHER_TRANSFORMATION)
            val inputByte = inString?.toByteArray(charset(charset))
            when (type) {
                CRYPTO_TYPE_DECRYPT -> {
                    cipher!!.init(Cipher.DECRYPT_MODE, initKey(hashKey, salt))
                    return String(cipher.doFinal(Base64.decode(inputByte, Base64.DEFAULT)))
                }
                CRYPTO_TYPE_ENCRYPT -> {
                    cipher!!.init(Cipher.ENCRYPT_MODE, initKey(hashKey, salt))
                    return String(Base64.encode(cipher.doFinal(inputByte), Base64.DEFAULT))
                }
            }
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        }

        return null
    }

    @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    private fun getSecretKey(password: CharArray, salt: ByteArray): SecretKey {
        val factory = SecretKeyFactory.getInstance(GENERATE_KEY__ALGORITHM)
        val spec = PBEKeySpec(password, salt, 1024, 128)
        val tmp = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, GENERATE_KEY_ALGORITHM)
    }

    private fun initKey(hashKey: String, salt: String): SecretKey? {
        try {
            return getSecretKey(hashKey.toCharArray(), salt.toByteArray())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        }

        return null
    }
}// Empty private constructor.