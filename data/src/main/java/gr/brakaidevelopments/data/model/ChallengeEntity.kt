package gr.brakaidevelopments.data.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import gr.brakaidevelopments.domain.models.ChallengeState
import gr.brakaidevelopments.domain.models.LocationEntity
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "Challenge")
data class ChallengeEntity(
    @PrimaryKey @ColumnInfo(name = "challenge_id") @SerializedName("challenge_id")
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title") @SerializedName("title")
    var title: String,
    @ColumnInfo(name = "sub_title") @SerializedName("sub_title")
    var subTitle: String,
    @ColumnInfo(name = "description") @SerializedName("description")
    var description: String,
    @Embedded @SerializedName("location")
    var location: LocationEntity,
    @SerializedName("challenge_state")
    var challengeState: ChallengeState,
    @ColumnInfo(name = "up_votes") @SerializedName("up_votes")
    var upVotes: Long = 0,
    @ColumnInfo(name = "down_votes") @SerializedName("down_votes")
    var downVotes: Long = 0,
    @ColumnInfo(name = "cover_image") @SerializedName("cover_image")
    var coverImage: Uri
) : Parcelable