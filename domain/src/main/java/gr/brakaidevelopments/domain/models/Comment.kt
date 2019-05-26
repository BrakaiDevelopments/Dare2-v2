package gr.brakaidevelopments.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Comment(
    val id: UUID = UUID.randomUUID(),
    val user: User,
    val challengeID: UUID,
    val parentID: UUID,
    var message: String,
    var UpVotes: Long = 0,
    var DownVotes: Long = 0,
    var createdAt: Date
) : Parcelable