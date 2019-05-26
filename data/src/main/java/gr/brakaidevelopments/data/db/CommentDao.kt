package gr.brakaidevelopments.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import gr.brakaidevelopments.data.model.CommentEntity
import java.util.*

@Dao
abstract class CommentDao : BaseDao<CommentEntity> {

    @Query("SELECT * FROM Comment WHERE comment_id=:commentID")
    abstract suspend fun getCommentByID(commentID: UUID): CommentEntity?

    @Query("SELECT * FROM Comment WHERE comment_id=:commentID")
    abstract fun getCommentByIDLiveData(commentID: UUID): LiveData<CommentEntity?>

    @Query("SELECT * FROM Comment WHERE parent_challenge_id=:challengeId")
    abstract suspend fun getCommentByChallengeID(challengeId: UUID): List<CommentEntity>

    @Query("SELECT * FROM Comment WHERE parent_challenge_id=:challengeId")
    abstract fun getCommentByChallengeIDLiveData(challengeId: UUID): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM Comment WHERE parent_challenge_id=:challengeId")
    abstract fun getCommentByChallengeIDPaged(challengeId: UUID): DataSource.Factory<Int ,CommentEntity>

    @Query("SELECT * FROM Comment WHERE user_id =:userId")
    abstract suspend fun getCommentsByUserID(userId: UUID): List<CommentEntity>

    @Query("SELECT * FROM Comment WHERE user_id =:userId")
    abstract fun getCommentsByUserIDLiveData(userId: UUID): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM Comment WHERE message LIKE :message")
    abstract suspend fun getCommentsByMessage(message: String): List<CommentEntity>

}