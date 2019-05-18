/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.db

import android.net.Uri
import androidx.room.Dao
import androidx.room.Query
import gr.brakaidevelopments.data.model.UserCredentials
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.data.model.UserProfileState
import java.util.*

@Dao
abstract class UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM Users WHERE id=:userId")
    abstract suspend fun getUserByID(userId: UUID): UserEntity

    @Query("SELECT * FROM Users WHERE email=:email")
    abstract suspend fun getUserByEmail(email: String): UserEntity

    @Query("SELECT * FROM Users WHERE username LIKE :email")
    abstract suspend fun getUserByUsername(email: String): UserEntity

    @Query("SELECT profile_image FROM Users WHERE id=:userId")
    abstract suspend fun getUserProfileImage(userId: UUID): Uri

    @Query("SELECT profile_image FROM Users WHERE id=:userId")
    abstract suspend fun getUserStatus(userId: UUID): UserProfileState

    @Query("SELECT * FROM Users WHERE countryName LIKE :countryName ")
    abstract suspend fun getUserByCountryName(countryName: String): UserEntity

    @Query("SELECT username, password FROM Users WHERE id=:userId")
    abstract suspend fun getUserCredentialsByID(userId: UUID): UserCredentials

}