/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.domain.utils

import com.orhanobut.logger.Logger
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface Callback<T> {
    fun onComplete(result: T)
    fun onException(e: Exception?)
}

suspend fun <T> awaitCallback(block: (Callback<T>) -> Unit): T =
    suspendCancellableCoroutine { cont ->
        block(object : Callback<T> {
            override fun onComplete(result: T) {
                Logger.w("Coroutine continue with: $result")
                cont.resume(result)
            }

            override fun onException(e: Exception?) {
                e?.let {
                    cont.resumeWithException(it)
                    Logger.e(it, "awaitCallback coroutine error")
                }
            }
        })
    }