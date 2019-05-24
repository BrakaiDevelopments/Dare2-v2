package gr.brakaidevelopments.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import gr.brakaidevelopments.data.model.LeaderBoardEntity
import java.util.*

@Dao
abstract class LeaderBoardDao : BaseDao<LeaderBoardEntity> {

    @Query("SELECT * FROM LeaderBoard")
    abstract suspend fun getAllLeaderBoard() : List<LeaderBoardEntity>

    @Query("SELECT * FROM LeaderBoard")
    abstract fun getAllLeaderBoardLiveData(): LiveData<List<LeaderBoardEntity>>

    @Query("SELECT * FROM LeaderBoard")
    abstract fun getAllLeaderBoardPaged(): DataSource.Factory<Int, LeaderBoardEntity>

    @Query("SELECT * FROM LeaderBoard WHERE leaderBoard_id=:leaderBoardId")
    abstract suspend fun getLeaderBoardByID(leaderBoardId: UUID): LeaderBoardEntity?

    @Query("SELECT * FROM LeaderBoard WHERE leaderBoard_id=:leaderBoardId")
    abstract fun getLeaderBoardByIDLiveData(leaderBoardId: UUID): LiveData<LeaderBoardEntity?>

    @Query("SELECT points FROM LeaderBoard JOIN User ON User.leaderBoard_id = LeaderBoard.leaderBoard_id WHERE User.user_id = :userId")
    abstract suspend fun getUserPoints(userId: UUID): Long?

    @Query("SELECT points FROM LeaderBoard JOIN User ON User.leaderBoard_id = LeaderBoard.leaderBoard_id WHERE User.user_id = :userId")
    abstract fun getUserPointsLiveData(userId: UUID): LiveData<Long?>

    @Query("SELECT challenges_participated FROM LeaderBoard JOIN User ON User.leaderBoard_id = LeaderBoard.leaderBoard_id WHERE User.user_id = :userId")
    abstract suspend fun getUserParticipates(userId: UUID): Long?

    @Query("SELECT challenges_participated FROM LeaderBoard JOIN User ON User.leaderBoard_id = LeaderBoard.leaderBoard_id WHERE User.user_id = :userId")
    abstract fun getUserParticipatesLiveData(userId: UUID): LiveData<Long?>

    @Query("SELECT challenges_completed FROM LeaderBoard JOIN User ON User.leaderBoard_id = LeaderBoard.leaderBoard_id WHERE User.user_id = :userId")
    abstract suspend fun getUserCompletedChallenges(userId: UUID): Long?

    @Query("SELECT challenges_completed FROM LeaderBoard JOIN User ON User.leaderBoard_id = LeaderBoard.leaderBoard_id WHERE User.user_id = :userId")
    abstract fun getUserCompletedChallengesLiveData(userId: UUID): LiveData<Long?>

}