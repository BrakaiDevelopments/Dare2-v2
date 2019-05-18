/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.api


import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DaretoApi {
    @POST("/users/register")
    @Headers("Content-Type: application/json")
    suspend fun createUserAsync(@Body userData: Unit): Deferred<Unit>


}