package gr.brakaidevelopments.data.db

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import gr.brakaidevelopments.data.model.ChallengeEntity
import gr.brakaidevelopments.data.model.ChallengeState
import java.util.*

@Dao
abstract class ChallengeDao : BaseDao<ChallengeEntity> {

    @Query("SELECT * FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeById(challengeId: UUID): ChallengeEntity?

    @Query("SELECT * FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeByIdLiveData(challengeId: UUID): LiveData<ChallengeEntity>

    @Query("SELECT * FROM challenge WHERE title LIKE :title OR sub_title LIKE :title")
    abstract suspend fun getChallengesByTitleOrSubTitle(title: String): List<ChallengeEntity>

    @Query("SELECT * FROM challenge WHERE status =:status")
    abstract suspend fun getChallengeByStatus(status: ChallengeState): List<ChallengeEntity>

    @Query("SELECT up_votes FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeUpVotesById(challengeId: UUID): Long?

    @Query("SELECT up_votes FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeUpVotesByIdLiveData(challengeId: UUID): LiveData<Long>

    @Query("SELECT down_votes FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeDownVotesById(challengeId: UUID): Long

    @Query("SELECT down_votes FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeDownVotesByIdLiveData(challengeId: UUID): LiveData<Long>

    @Query("SELECT cover_image FROM challenge WHERE challenge_id =:challengeId")
    abstract suspend fun getChallengeCoverImageyId(challengeId: UUID): Uri?


}