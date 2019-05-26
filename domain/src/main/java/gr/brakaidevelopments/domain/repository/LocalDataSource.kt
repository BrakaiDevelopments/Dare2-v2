package gr.brakaidevelopments.domain.repository

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import gr.brakaidevelopments.domain.models.*
import java.util.*

interface LocalDataSource {

    suspend fun findUserByID(userId: UUID): User?

    suspend fun observeUserById(userId: UUID): LiveData<User?>

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

    suspend fun getAllLeaderBoardEntries(): List<LeaderBoardEntry>

    suspend fun getLeaderBoardEntry(leaderBoardEntryId: UUID): LeaderBoardEntry?

    suspend fun observeLeaderBoardEntry(leaderBoardEntryId: UUID): LiveData<LeaderBoardEntry?>

    suspend fun getAllLeaderBoardEntriesPaged(): DataSource.Factory<Int, LeaderBoardEntry>

    suspend fun observeAllLeaderBoardEntries(): LiveData<List<LeaderBoardEntry>>

    suspend fun getUserPoints(userId: UUID): Long?

    suspend fun observeUserPoints(userId: UUID): LiveData<Long?>

    suspend fun getUserNumOfParticipates(userId: UUID): Long?

    suspend fun observeUserNumOfParticipates(userId: UUID): LiveData<Long?>

    suspend fun getUserNumOfCompletedChallenges(userId: UUID): Long?

    suspend fun observeUserNumOfCompletedChallenges(userId: UUID): LiveData<Long?>

    suspend fun insertLeaderBoardEntry(leaderBoardEntry: LeaderBoardEntry): Long

    suspend fun insertLeaderBoardEntries(vararg leaderBoardEntries: LeaderBoardEntry): List<Long>

    suspend fun insertLeaderBoardEntries(leaderBoardEntries: List<LeaderBoardEntry>): List<Long>

    suspend fun updateLeaderBoardEntry(leaderBoardEntry: LeaderBoardEntry): Int

    suspend fun deleteLeaderBoardEntry(leaderBoardEntry: LeaderBoardEntry): Int

    suspend fun getCommentsByParentID(commentId: UUID): List<Comment>

    suspend fun getCommentsByParentIDPaged(commentId: UUID): DataSource.Factory<Int, Comment>

    suspend fun observeCommentsByParentId(commentId: UUID): LiveData<List<Comment>>

    suspend fun getCommentByID(commentId: UUID): Comment?

    suspend fun observeCommentByID(commentId: UUID): LiveData<Comment?>

    suspend fun getCommentByChallengeID(challengeID: UUID): List<Comment>

    suspend fun observeCommentByChallengeID(challengeID: UUID): LiveData<List<Comment>>

    suspend fun getCommentByChallengeIDPaged(challengeID: UUID): DataSource.Factory<Int, Comment>

    suspend fun getCommentByUserID(userId: UUID): List<Comment>

    suspend fun observeCommentByUserID(userId: UUID): LiveData<List<Comment>>

    suspend fun getCommentByMessage(message: String): List<Comment>

    suspend fun insertComment(comment: Comment): Long

    suspend fun insertComments(vararg comments: Comment): List<Long>

    suspend fun insertComments(comments: List<Comment>): List<Long>

    suspend fun updateComment(comment: Comment): Int

    suspend fun deleteComment(comment: Comment): Int

    suspend fun getAllChallenges(): List<Challenge>

    suspend fun getAllChallengesPaged(): DataSource.Factory<Int, Challenge>

    suspend fun observeAllChallenges(): LiveData<List<Challenge>>

    suspend fun getChallengeByID(challengeID: UUID): Challenge?

    suspend fun observeChallengeByID(challengeID: UUID): LiveData<Challenge>

    suspend fun getChallengeByTittleOrSubtittle(query: String): List<Challenge>

    suspend fun getChallengeByState(challengeState: ChallengeState): List<Challenge>

    suspend fun getChallengeByStatePaged(challengeState: ChallengeState): DataSource.Factory<Int, Challenge>

    suspend fun observeChallengeByState(challengeState: ChallengeState): LiveData<List<Challenge>>

    suspend fun getChallengeUpVotes(challengeID: UUID): Long?

    suspend fun observeChallengeUpVotes(challengeID: UUID): LiveData<Long?>

    suspend fun getChallengeDownVotes(challengeID: UUID): Long?

    suspend fun observeChallengeDownVotes(challengeID: UUID): LiveData<Long?>

    suspend fun getChallengeProfileImage(challengeID: UUID): Uri?

    suspend fun insertChallenge(challenge: Challenge): Long

    suspend fun insertChallenges(vararg challenges: Challenge): List<Long>

    suspend fun insertChallenges(challenges: List<Challenge>): List<Long>

    suspend fun updateChallenge(challenge: Challenge): Int

    suspend fun deleteChallenge(challenge: Challenge): Int

}