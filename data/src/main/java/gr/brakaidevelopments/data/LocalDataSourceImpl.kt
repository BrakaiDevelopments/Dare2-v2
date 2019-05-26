package gr.brakaidevelopments.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import gr.brakaidevelopments.data.db.*
import gr.brakaidevelopments.data.utils.*
import gr.brakaidevelopments.domain.models.*
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

    override suspend fun observeUserById(userId: UUID): LiveData<User?> {
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

    override suspend fun getAllLeaderBoardEntries(): List<LeaderBoardEntry> {
        return leaderBoardDao.getAllLeaderBoard().map { it.asLeaderBoardEntry() }
    }

    override suspend fun getLeaderBoardEntry(leaderBoardEntryId: UUID): LeaderBoardEntry? {
        return leaderBoardDao.getLeaderBoardByID(leaderBoardEntryId)?.asLeaderBoardEntry()
    }

    override suspend fun observeLeaderBoardEntry(leaderBoardEntryId: UUID): LiveData<LeaderBoardEntry?> {
        return Transformations.map(leaderBoardDao.getLeaderBoardByIDLiveData(leaderBoardEntryId)) { it?.asLeaderBoardEntry() }
    }

    override suspend fun getAllLeaderBoardEntriesPaged(): DataSource.Factory<Int, LeaderBoardEntry> {
        return leaderBoardDao.getAllLeaderBoardPaged().map { it.asLeaderBoardEntry() }
    }

    override suspend fun observeAllLeaderBoardEntries(): LiveData<List<LeaderBoardEntry>> {
        return Transformations.map(leaderBoardDao.getAllLeaderBoardLiveData()) { it.map { item -> item.asLeaderBoardEntry() } }
    }

    override suspend fun getUserPoints(userId: UUID): Long? {
        return leaderBoardDao.getUserPoints(userId)
    }

    override suspend fun observeUserPoints(userId: UUID): LiveData<Long?> {
        return leaderBoardDao.getUserPointsLiveData(userId)
    }

    override suspend fun getUserNumOfParticipates(userId: UUID): Long? {
        return leaderBoardDao.getUserParticipates(userId)
    }

    override suspend fun observeUserNumOfParticipates(userId: UUID): LiveData<Long?> {
        return leaderBoardDao.getUserParticipatesLiveData(userId)
    }

    override suspend fun getUserNumOfCompletedChallenges(userId: UUID): Long? {
        return leaderBoardDao.getUserCompletedChallenges(userId)
    }

    override suspend fun observeUserNumOfCompletedChallenges(userId: UUID): LiveData<Long?> {
        return leaderBoardDao.getUserCompletedChallengesLiveData(userId)
    }

    override suspend fun insertLeaderBoardEntry(leaderBoardEntry: LeaderBoardEntry): Long {
        return leaderBoardDao.insertItem(leaderBoardEntry.asLeaderBoardEntity())
    }

    override suspend fun insertLeaderBoardEntries(vararg leaderBoardEntries: LeaderBoardEntry): List<Long> {
        return leaderBoardDao.insertItems(leaderBoardEntries.map { it.asLeaderBoardEntity() })
    }

    override suspend fun insertLeaderBoardEntries(leaderBoardEntries: List<LeaderBoardEntry>): List<Long> {
        return leaderBoardDao.insertItems(leaderBoardEntries.map { it.asLeaderBoardEntity() })
    }

    override suspend fun updateLeaderBoardEntry(leaderBoardEntry: LeaderBoardEntry): Int {
        return leaderBoardDao.updateItem(leaderBoardEntry.asLeaderBoardEntity())
    }

    override suspend fun deleteLeaderBoardEntry(leaderBoardEntry: LeaderBoardEntry): Int {
        return leaderBoardDao.deleteItem(leaderBoardEntry.asLeaderBoardEntity())
    }

    override suspend fun getCommentsByParentID(commentId: UUID): List<Comment> {
        return commentViewDao.getCommentsByParentId(commentId).map { it.asComment() }
    }

    override suspend fun getCommentsByParentIDPaged(commentId: UUID): DataSource.Factory<Int, Comment> {
        return commentViewDao.getCommentsByParentIdPaged(commentId).map { it.asComment() }
    }

    override suspend fun observeCommentsByParentId(commentId: UUID): LiveData<List<Comment>> {
        return Transformations.map(commentViewDao.getCommentsByParentIdLiveData(commentId)) { it.map { item -> item.asComment() } }
    }
}