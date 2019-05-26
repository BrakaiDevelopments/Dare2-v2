package gr.brakaidevelopments.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
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

    override suspend fun getCommentByID(commentId: UUID): Comment? {
        return commentDao.getCommentByID(commentId)?.asComment()
    }

    override suspend fun observeCommentByID(commentId: UUID): LiveData<Comment?> {
        return Transformations.map(commentDao.getCommentByIDLiveData(commentId)) { it?.asComment() }
    }

    override suspend fun getCommentByChallengeID(challengeID: UUID): List<Comment> {
        return commentDao.getCommentByChallengeID(challengeID).map { it.asComment() }
    }

    override suspend fun observeCommentByChallengeID(challengeID: UUID): LiveData<List<Comment>> {
        return Transformations.map(commentDao.getCommentByChallengeIDLiveData(challengeID)) { it.map { item -> item.asComment() } }
    }

    override suspend fun getCommentByChallengeIDPaged(challengeID: UUID): DataSource.Factory<Int, Comment> {
        return commentDao.getCommentByChallengeIDPaged(challengeID).map { it.asComment() }
    }

    override suspend fun getCommentByUserID(userId: UUID): List<Comment> {
        return commentDao.getCommentsByUserID(userId).map { it.asComment() }
    }

    override suspend fun observeCommentByUserID(userId: UUID): LiveData<List<Comment>> {
        return Transformations.map(commentDao.getCommentsByUserIDLiveData(userId)) { it.map { item -> item.asComment() } }
    }

    override suspend fun getCommentByMessage(message: String): List<Comment> {
        return commentDao.getCommentsByMessage(message).map { it.asComment() }
    }

    override suspend fun insertComment(comment: Comment): Long {
        return commentDao.insertItem(comment.asCommentEntity())
    }

    override suspend fun insertComments(vararg comments: Comment): List<Long> {
        return commentDao.insertItems(comments.map { it.asCommentEntity() })
    }

    override suspend fun insertComments(comments: List<Comment>): List<Long> {
        return commentDao.insertItems(comments.map { it.asCommentEntity() })
    }

    override suspend fun updateComment(comment: Comment): Int {
        return commentDao.updateItem(comment.asCommentEntity())
    }

    override suspend fun deleteComment(comment: Comment): Int {
        return commentDao.deleteItem(comment.asCommentEntity())
    }

    override suspend fun getAllChallenges(): List<Challenge> {
        return challengeDao.getAllChallenges().map { it.asChallenge() }
    }

    override suspend fun getAllChallengesPaged(): DataSource.Factory<Int, Challenge> {
        return challengeDao.getAllChallengesPaged().map { it.asChallenge() }
    }

    override suspend fun observeAllChallenges(): LiveData<List<Challenge>> {
        return Transformations.map(challengeDao.getAllChallengesLiveData()) { it.map { item -> item.asChallenge() } }
    }

    override suspend fun getChallengeByID(challengeID: UUID): Challenge? {
        return challengeDao.getChallengeById(challengeID)?.asChallenge()
    }

    override suspend fun observeChallengeByID(challengeID: UUID): LiveData<Challenge> {
        return Transformations.map(challengeDao.getChallengeByIdLiveData(challengeID)) { it.asChallenge() }
    }

    override suspend fun getChallengeByTittleOrSubtittle(query: String): List<Challenge> {
        return challengeDao.getChallengesByTitleOrSubTitle(query).map { it.asChallenge() }
    }

    override suspend fun getChallengeByState(challengeState: ChallengeState): List<Challenge> {
        return challengeDao.getChallengeByStatus(challengeState).map { it.asChallenge() }
    }

    override suspend fun getChallengeByStatePaged(challengeState: ChallengeState): DataSource.Factory<Int, Challenge> {
        return challengeDao.getChallengeByStatusPaged(challengeState).map { it.asChallenge() }
    }

    override suspend fun observeChallengeByState(challengeState: ChallengeState): LiveData<List<Challenge>> {
        return challengeDao.getChallengeByStatusLiveData(challengeState).map { it.map { item -> item.asChallenge() } }
    }

    override suspend fun getChallengeUpVotes(challengeID: UUID): Long? {
        return challengeDao.getChallengeUpVotesById(challengeID)
    }

    override suspend fun observeChallengeUpVotes(challengeID: UUID): LiveData<Long?> {
        return challengeDao.getChallengeUpVotesByIdLiveData(challengeID)
    }

    override suspend fun getChallengeDownVotes(challengeID: UUID): Long? {
        return challengeDao.getChallengeDownVotesById(challengeID)
    }

    override suspend fun observeChallengeDownVotes(challengeID: UUID): LiveData<Long?> {
        return challengeDao.getChallengeDownVotesByIdLiveData(challengeID)
    }

    override suspend fun getChallengeProfileImage(challengeID: UUID): Uri? {
        return challengeDao.getChallengeCoverImageId(challengeID)
    }

    override suspend fun insertChallenge(challenge: Challenge): Long {
        return challengeDao.insertItem(challenge.asChallengeEntity())
    }

    override suspend fun insertChallenges(vararg challenges: Challenge): List<Long> {
        return challengeDao.insertItems(challenges.map { it.asChallengeEntity() })
    }

    override suspend fun insertChallenges(challenges: List<Challenge>): List<Long> {
        return challengeDao.insertItems(challenges.map { it.asChallengeEntity() })
    }

    override suspend fun updateChallenge(challenge: Challenge): Int {
        return challengeDao.updateItem(challenge.asChallengeEntity())
    }

    override suspend fun deleteChallenge(challenge: Challenge): Int {
        return challengeDao.deleteItem(challenge.asChallengeEntity())
    }
}