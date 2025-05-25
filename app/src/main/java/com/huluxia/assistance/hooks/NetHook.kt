package com.huluxia.assistance.hooks

import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.param.PackageParam
import com.huluxia.assistance.DEVELOPER_SSL_IGNORE_KEY
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.security.cert.X509Certificate


/**
 * @author: 迷路的小孩
 * @date: 2025/5/25
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
private val SSL_CLASS_NAME = "com.android.org.conscrypt.TrustManagerImpl"
private val SSL_METHOD_NAME = "checkTrustedRecursive"
private val SSL_RETURN_TYPE = List::class.java
private val SSL_RETURN_PARAM_TYPE = X509Certificate::class.java

fun PackageParam.netHook() {
    if (appContext?.prefs()?.native()?.getBoolean(DEVELOPER_SSL_IGNORE_KEY, false) == true) {
        var hookedMethods = 0

        SSL_CLASS_NAME.toClass().declaredMethods.forEach { method ->
            if (!checkSSLMethod(method)) return@forEach

            SSL_CLASS_NAME.toClass().declaredMethods.forEach { method ->
                if (!checkSSLMethod(method)) return@forEach

                method.hook {
                    replaceTo(emptyList<X509Certificate>())
                }

                hookedMethods++
            }
        }
    }
}

private fun checkSSLMethod(method: Method): Boolean {
    if (method.name != SSL_METHOD_NAME) return false
    if (!SSL_RETURN_TYPE.isAssignableFrom(method.returnType)) return false
    val returnType = method.genericReturnType
    if (returnType !is ParameterizedType) return false
    val args = returnType.actualTypeArguments
    return !(args.size != 1 || args[0] != SSL_RETURN_PARAM_TYPE)
}
