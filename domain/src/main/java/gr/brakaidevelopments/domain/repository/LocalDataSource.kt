package gr.brakaidevelopments.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import gr.brakaidevelopments.domain.models.User
import gr.brakaidevelopments.domain.models.UserCredentials
import gr.brakaidevelopments.domain.models.UserProfileState
import java.util.*

interface LocalDataSource {

    suspend fun findUserByID(userId: UUID): User?

    suspend fun observeUserById(userId: UUID): LiveData<User>

    suspend fun findUserByEmail(email: String): User?

    suspend fun findUserByUsername(username: String): User?

    suspend fun getUserProfileImage(userId: UUID): Uri?

    suspend fun getUserStatus(userId: UUID): UserProfileState?

    suspend fun getUsersWithStatus(status: UserProfileState): List<User>

    suspend fun findUsersByCountryName(countryName: String): List<User>

    suspend fun getUserCredentials(userId: UUID): UserCredentials?

    suspend fun insertUser(user: User): Long

    suspend fun insertUsers(vararg users: User): List<Long>

    suspend fun insertUsers(users: List<User>): List<Long>

    suspend fun updateUser(user: User): Int

    suspend fun deleteUser(user: User): Int

}