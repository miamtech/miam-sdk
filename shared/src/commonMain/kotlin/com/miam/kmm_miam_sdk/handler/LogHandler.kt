package com.miam.kmm_miam_sdk.handler

import kotlin.native.concurrent.ThreadLocal

class LogHandler {

    @ThreadLocal
    companion object {
        fun init(
            debug: (String) -> Unit = this.debug,
            info: (String) -> Unit = this.info,
            warn: (String) -> Unit = this.warn,
            error: (String) -> Unit = this.error
        ){
            this.debug = debug
            this.info = info
            this.warn = warn
            this.error = error
        }

        var debug = fun(msg: String) {
            println("[DEBUG] $msg")
        }

        var info = fun(msg: String) {
            println("[INFO] $msg")
        }

        var warn = fun(msg: String) {
            println("[WARN] $msg")
        }

        var error = fun(msg: String) {
            println("[ERROR] $msg")
        }
    }
}