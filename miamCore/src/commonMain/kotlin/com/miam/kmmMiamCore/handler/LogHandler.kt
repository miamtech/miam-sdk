package com.miam.kmmMiamCore.handler

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
            println("[Miam][DEBUG] $msg")
        }

        var info = fun(msg: String) {
            println("[Miam][INFO] $msg")
        }

        var warn = fun(msg: String) {
            println("[Miam][WARN] $msg")
        }

        var error = fun(msg: String) {
            println("[Miam][ERROR] $msg")
        }
    }
}