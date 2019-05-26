package gr.brakaidevelopments.domain.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
    val id: UUID = UUID.randomUUID(),
    var username: String,
    var password: String,
    var email: String,
    var birthday: Date,
    var country: CountryEntity,
    var leaderBoardId: UUID,
    var profileImage: Uri,
    var userProfileState: UserProfileState
) : Parcelable