/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.domain.base

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T> : CoroutineScope {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    override val coroutineContext: CoroutineContext
        get() = scope.coroutineContext


    @ExperimentalCoroutinesApi
    fun cancelAll() {
        scope.cancel()
    }

}