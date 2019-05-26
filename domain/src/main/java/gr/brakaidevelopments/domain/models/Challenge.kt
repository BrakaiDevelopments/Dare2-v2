package gr.brakaidevelopments.domain.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Challenge(
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var subTitle: String,
    var description: String,
    var location: Location,
    var challengeState: ChallengeState,
    var upVotes: Long = 0,
    var downVotes: Long = 0,
    var coverImage: Uri
) : Parcelable