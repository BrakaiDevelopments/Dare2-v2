/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.dare2.utils

object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource =
        SimpleCountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}