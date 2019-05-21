package gr.brakaidevelopments.data.model

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "Comment",
    indices = [Index(value = ["message"], unique = false)],
    foreignKeys = [ForeignKey(
        entity = ChallengeEntity::class,
        parentColumns = ["challenge_id"],
        childColumns = ["parent_challenge_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CommentEntity(
    @PrimaryKey @ColumnInfo(name = "comment_id") @SerializedName("comment_id")
    val id: UUID = UUID.randomUUID(),
    @Embedded
    val user: UserEntity,
    @ColumnInfo(name = "parent_challenge_id") @SerializedName("parent_challenge_id")
    val challengeID: UUID,
    @ColumnInfo(name = "parent_id") @SerializedName("parent_id")
    val parentID: UUID,
    @ColumnInfo(name = "message") @SerializedName("message")
    var message: String,
    @ColumnInfo(name = "up_votes") @SerializedName("up_votes")
    var UpVotes: Long = 0,
    @ColumnInfo(name = "down_votes") @SerializedName("down_votes")
    var DownVotes: Long = 0,
    @ColumnInfo(name = "created_at") @SerializedName("created_at")
    var createdAt: Date
): Parcelable