package com.miam.kmmMiamCore.handler

import kotlin.native.concurrent.ThreadLocal

public class LogHandler {

    @ThreadLocal
    public companion object {
        public fun init(
            debug: (String) -> Unit = this.debug,
            info: (String) -> Unit = this.info,
            warn: (String) -> Unit = this.warn,
            error: (String) -> Unit = this.error
        ) {
            this.debug = debug
            this.info = info
            this.warn = warn
            this.error = error
        }

        public var debug: (String) -> Unit = fun(msg: String) {
            println("[Miam][DEBUG] $msg")
        }

        public var info: (String) -> Unit = fun(msg: String) {
            println("[Miam][INFO] $msg")
        }

        public var warn: (String) -> Unit = fun(msg: String) {
            println("[Miam][WARN] $msg")
        }

        public var error: (String) -> Unit = fun(msg: String) {
            println("[Miam][ERROR] $msg")
        }
    }
}