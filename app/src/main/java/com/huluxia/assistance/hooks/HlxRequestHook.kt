package com.huluxia.assistance.hooks

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.ENCRYPT_LOGIN_SALT
import com.huluxia.assistance.ENCRYPT_TOPIC_KEY
import java.security.MessageDigest
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * @author: 迷路的小孩
 * @date: 2025/5/17
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
@RequiresApi(Build.VERSION_CODES.O)
fun PackageParam.hlxRequestHook() {
    val decryptObj = "com.huluxia.compressor.HlxRequestSign".toClass().method {
        name = "bbsDecrypt"
        modifiers {
            isPrivate
            isStatic
        }
    }
    YLog.debug("HlxRequestHook对象>>>>$decryptObj")

    decryptObj.hook {
        before {
            var content = args[0] as String
            val decryptedContent = decrypt(content)
            result = decryptedContent
//            YLog.debug("HlxRequestHook对象>>>>解密结果:  $decryptedContent")
        }
    }

    val generateSignatureObj = "com.huluxia.compressor.HlxRequestSign".toClass().method {
        name = "generateSignature"
        modifiers {
            isPrivate
            isStatic
        }
    }

    YLog.debug("generateSignature对象>>>>$generateSignatureObj")

    generateSignatureObj.hook {
        before {
            val content = args[0] as String
            val encryptResult = md5Encrypt(content + ENCRYPT_LOGIN_SALT)
            result = encryptResult
//            YLog.debug("generateSignature对象>>>>加密原文:  ${content + ENCRYPT_LOGIN_SALT}")
//            YLog.debug("generateSignature对象>>>>加密结果:  $encryptResult")
        }
    }
}

private fun getKeyFromString(keyString: String): SecretKey {
    val keyBytes = keyString.toByteArray(Charsets.UTF_8)
    return SecretKeySpec(keyBytes, "AES")
}

@SuppressLint("GetInstance")
@RequiresApi(Build.VERSION_CODES.O)
private fun encrypt(
    plaintext: String, key: SecretKey = getKeyFromString(ENCRYPT_TOPIC_KEY)
): String {
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val encryptedBytes = cipher.doFinal(plaintext.toByteArray())
    return Base64.getMimeEncoder().encodeToString(encryptedBytes)
}

// 使用 AES/ECB/PKCS5Padding 解密
@RequiresApi(Build.VERSION_CODES.O)
private fun decrypt(
    encryptedText: String, key: SecretKey = getKeyFromString(ENCRYPT_TOPIC_KEY)
): String {
    return try {
        decryptData(encryptedText, key)
    } catch (e: Exception) {
        ""
    }
}

@SuppressLint("GetInstance")
@RequiresApi(Build.VERSION_CODES.O)
private fun decryptData(encryptedText: String, key: SecretKey): String {
    val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
    cipher.init(Cipher.DECRYPT_MODE, key)
    try {
        // 使用 MimeDecoder 来解码带换行符的 Base64 字符串
        val decodedBytes = Base64.getMimeDecoder().decode(encryptedText)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes)
    } catch (e: IllegalArgumentException) {
        YLog.error("HlxRequestHook>>>>decryptData>>无效的Base64")
        throw e
    }
}

fun md5Encrypt(input: String): String {
    // 获取 MD5 实例
    val messageDigest = MessageDigest.getInstance("MD5")

    // 获取输入字符串的字节数组，并进行加密
    val hashBytes = messageDigest.digest(input.toByteArray())

    // 转换成 16 进制字符串
    val stringBuilder = StringBuilder()
    for (byte in hashBytes) {
        val hex = String.format("%02x", byte)
        stringBuilder.append(hex)
    }

    return stringBuilder.toString()
}