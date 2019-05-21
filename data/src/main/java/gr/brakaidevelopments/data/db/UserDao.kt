/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.db

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import gr.brakaidevelopments.data.model.UserCredentials
import gr.brakaidevelopments.data.model.UserEntity
import gr.brakaidevelopments.data.model.UserProfileState
import java.util.*

@Dao
abstract class UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM User WHERE user_id=:userId")
    abstract suspend fun getUserByID(userId: UUID): UserEntity?

    @Query("SELECT * FROM User WHERE user_id=:userId")
    abstract fun getUserByIDLiveData(userId: UUID): LiveData<UserEntity>

    @Query("SELECT * FROM User WHERE email=:email")
    abstract suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM User WHERE email=:email")
    abstract fun getUserByEmailLiveData(email: String): LiveData<UserEntity>

    @Query("SELECT * FROM User WHERE username LIKE :email")
    abstract suspend fun getUserByUsername(email: String): UserEntity?

    @Query("SELECT profile_image FROM User WHERE user_id=:userId")
    abstract suspend fun getUserProfileImage(userId: UUID): Uri?

    @Query("SELECT userProfileState FROM User WHERE user_id=:userId")
    abstract suspend fun getUserStatus(userId: UUID): UserProfileState?

    @Query("SELECT * FROM User WHERE userProfileState = :state")
    abstract suspend fun getUserByStatus(state: UserProfileState): List<UserEntity>

    @Query("SELECT * FROM User WHERE countryName LIKE :countryName ")
    abstract suspend fun getUserByCountryName(countryName: String): List<UserEntity>

    @Query("SELECT username, password FROM User WHERE user_id=:userId")
    abstract suspend fun getUserCredentialsByID(userId: UUID): UserCredentials?

}