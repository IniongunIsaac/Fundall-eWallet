package com.fundall.ewallet.utils

import kotlinx.coroutines.*

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}