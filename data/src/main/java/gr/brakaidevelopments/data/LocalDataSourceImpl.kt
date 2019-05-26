package gr.brakaidevelopments.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import gr.brakaidevelopments.data.db.*
import gr.brakaidevelopments.data.utils.asUser
import gr.brakaidevelopments.data.utils.asUserEntity
import gr.brakaidevelopments.domain.models.User
import gr.brakaidevelopments.domain.models.UserCredentials
import gr.brakaidevelopments.domain.models.UserProfileState
import gr.brakaidevelopments.domain.repository.LocalDataSource
import java.util.*

class LocalDataSourceImpl(
    private val challengeDao: ChallengeDao,
    private val userDao: UserDao,
    private val commentViewDao: CommentViewDao,
    private val commentDao: CommentDao,
    private val leaderBoardDao: LeaderBoardDao
) : LocalDataSource {
    override suspend fun findUserByID(userId: UUID): User? {
        return userDao.getUserByID(userId)?.asUser()
    }

    override suspend fun observeUserById(userId: UUID): LiveData<User> {
        return Transformations.map(userDao.getUserByIDLiveData(userId)) { it?.asUser() }
    }

    override suspend fun findUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)?.asUser()
    }

    override suspend fun findUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)?.asUser()
    }

    override suspend fun getUserProfileImage(userId: UUID): Uri? {
        return userDao.getUserProfileImage(userId)
    }

    override suspend fun getUserStatus(userId: UUID): UserProfileState? {
        return userDao.getUserStatus(userId)
    }

    override suspend fun getUsersWithStatus(status: UserProfileState): List<User> {
        return userDao.getUserByStatus(status).map { it.asUser() }
    }

    override suspend fun findUsersByCountryName(countryName: String): List<User> {
        return userDao.getUserByCountryName(countryName).map { it.asUser() }
    }

    override suspend fun getUserCredentials(userId: UUID): UserCredentials? {
        return userDao.getUserCredentialsByID(userId)
    }

    override suspend fun insertUser(user: User): Long {
        return userDao.insertItem(user.asUserEntity())
    }

    override suspend fun insertUsers(vararg users: User): List<Long> {
        return userDao.insertItems(users.map { it.asUserEntity() })
    }

    override suspend fun insertUsers(users: List<User>): List<Long> {
        return userDao.insertItems(users.map { it.asUserEntity() })
    }

    override suspend fun updateUser(user: User): Int {
        return userDao.updateItem(user.asUserEntity())
    }

    override suspend fun deleteUser(user: User): Int {
        return userDao.deleteItem(user.asUserEntity())
    }
}