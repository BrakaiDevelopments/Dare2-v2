package gr.brakaidevelopments.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import gr.brakaidevelopments.data.model.CommentEntity
import java.util.*


@Dao
abstract class CommentViewDao {

    @Query("SELECT * FROM Comment WHERE parent_id = :commentID")
    abstract suspend fun getCommentsByParentId(commentID: UUID): List<CommentEntity>

    @Query("SELECT * FROM Comment WHERE parent_id = :commentID")
    abstract fun getCommentsByParentIdLiveData(commentID: UUID): LiveData<List<CommentEntity>>

}