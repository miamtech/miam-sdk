package com.miam.kmmMiamCore.helpers

public inline fun <T, R> letElse(p: T?, block: (T) -> R, elseBlock: (() -> R?)): R? {
    return if (p != null) block(p) else elseBlock()
}

public inline fun <T1: Any, T2: Any, R: Any> dualLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}
