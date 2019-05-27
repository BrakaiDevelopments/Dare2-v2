/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.api


import com.google.gson.JsonObject
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.domain.models.LeaderBoardEntry
import gr.brakaidevelopments.domain.models.UserProfileState
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface DaretoApi {

    @GET("/users/id/{id}")
    @Headers("Content-Type: application/json")
    suspend fun getUserByID(@Path("id") userID: UUID): Response<UserEntity>

    @GET("/users/username/{username}")
    @Headers("Content-Type: application/json")
    suspend fun getUserByUserName(@Path("username") username: String): Response<UserEntity>

    @GET("/users/{id}/status")
    @Headers("Content-Type: application/json")
    suspend fun getUserStatus(@Path("id") userID: UUID): Response<UserProfileState>

    @GET("/users/status/{status}")
    @Headers("Content-Type: application/json")
    suspend fun getUsersByStatus(@Path("status") status: UserProfileState): Response<List<UserEntity>>

    @GET("/users/country/{country}")
    @Headers("Content-Type: application/json")
    suspend fun getUsersByCountryName(@Path("country") country: String): Response<List<UserEntity>>

    @POST("/users")
    @Headers("Content-Type: application/json")
    suspend fun createUser(@Body user: UserEntity): Response<UserEntity>

    @POST("/users")
    @Headers("Content-Type: application/json")
    suspend fun updateUser(@Body user: JsonObject): Response<UserEntity>

    @DELETE("/users")
    @Headers("Content-Type: application/json")
    suspend fun deleteUser(@Body user: UserEntity): Response<UserEntity>

    @GET("/leaderboard")
    @Headers("Content-Type: application/json")
    suspend fun getAllLeaderboard(): Response<List<LeaderBoardEntry>>

    @GET("/leaderboard/id/{id}")
    @Headers("Content-Type: application/json")
    suspend fun getLeaderboardByID(@Path("id") leaderBoardID: UUID): Response<List<LeaderBoardEntry>>















}