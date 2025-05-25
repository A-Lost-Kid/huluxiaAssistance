package com.huluxia.assistance.hooks.resource

import com.highcapable.yukihookapi.hook.factory.toClass
import com.highcapable.yukihookapi.hook.log.YLog

/**
 * @author: 迷路的小孩
 * @date: 2025/5/18
 * @signature: 善始者实繁，克终者盖寡。
 * @description:
 */
object ResId {
    lateinit var packageName: String
        private set
    lateinit var bbc: Class<*>
        private set
    lateinit var bbh: Class<*>
        private set
    lateinit var bbn: Class<*>
        private set
    lateinit var bbj: Class<*>
        private set
    lateinit var bbe: Class<*>
        private set

    fun init(packageName: String, appClassLoader: ClassLoader?) {
        if (!this::bbh.isInitialized) {
            val suffix = if (packageName == "com.huluxia.gametools") {
                "com.huluxia.bbs.b$"
            } else {
                "com.huluxia.b.b$"
            }
            this.packageName = packageName
            this.bbc = (suffix + "c").toClass(appClassLoader)
            this.bbh = (suffix + "h").toClass(appClassLoader)
            this.bbn = (suffix + "n").toClass(appClassLoader)
            this.bbj = (suffix + "j").toClass(appClassLoader)
            this.bbe = (suffix + "e").toClass(appClassLoader)
        } else {
            YLog.error("ResId already initialized!")
        }
    }
}