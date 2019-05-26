package gr.brakaidevelopments.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class LeaderBoardEntry(
    val id: UUID = UUID.randomUUID(),
    var points: Long = 0,
    var num_of_participates: Long = 0,
    var completed_challenges: Long = 0
) : Parcelable