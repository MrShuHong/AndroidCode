package com.example.core

import android.os.Handler
import java.util.*

/**
 * File description.
 *
 * @author dsh
 * @date 2019-08-21
 */

class Configurator {
    companion object {
        private var LEMON_CONFIGS = HashMap<Any, Any>()
        private var HANDLER = Handler()
    }

    constructor() {
        LEMON_CONFIGS[ConfigKeys.CONFIG_READY] = false
        LEMON_CONFIGS[ConfigKeys.HANDLER] = HANDLER
    }

    fun withApiHost(host: String): Configurator {
        LEMON_CONFIGS[ConfigKeys.API_HOST] = host
        return this
    }

    internal fun <T> getConfiguration(key: Any): T {
        checkConfiguration()
        val value = LEMON_CONFIGS[key] ?: throw NullPointerException(key.toString() + "is NULL")
        return value as T
    }

    private fun checkConfiguration() {
        val isReday = LEMON_CONFIGS[ConfigKeys.CONFIG_READY] as Boolean
        if (!isReday) {
            throw RuntimeException("Configuration is no ready,please call configure!")
        }
    }

}

