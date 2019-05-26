/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import gr.brakaidevelopments.domain.models.CountryEntity
import gr.brakaidevelopments.domain.models.UserProfileState
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "User",
    foreignKeys = [ForeignKey(
        entity = LeaderBoardEntity::class,
        parentColumns = arrayOf("leaderBoard_id"),
        childColumns = arrayOf("leaderBoard_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [
        Index(value = ["countryName"], unique = false),
        Index(value = ["username"], unique = true),
        Index(value = ["email"], unique = true),
        Index(value = ["leaderBoard_id"], unique = false)
    ]
)
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "user_id") @SerializedName("user_id")
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "username") @SerializedName("username")
    var username: String,
    @ColumnInfo(name = "password") @SerializedName("password")
    var password: String,
    @ColumnInfo(name = "email") @SerializedName("email")
    var email: String,
    @ColumnInfo(name = "birthday") @SerializedName("birthday")
    var birthday: Date,
    @Embedded @SerializedName("country")
    var country: CountryEntity,
    @ColumnInfo(name = "leaderBoard_id") @SerializedName("leaderBoard_id")
    var leaderBoardId: UUID,
    @ColumnInfo(name = "profile_image") @SerializedName("profile_image")
    var profileImage: Uri,
    @SerializedName("user_status")
    var userProfileState: UserProfileState
) : Parcelable