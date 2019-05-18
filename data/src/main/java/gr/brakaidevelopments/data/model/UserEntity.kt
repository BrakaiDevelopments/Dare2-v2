/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.model

import android.net.Uri
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = "Users",
    primaryKeys = ["id_", "username"],
    foreignKeys = [ForeignKey(
        entity = LeaderBoard::class,
        parentColumns = arrayOf("leaderBoard_id"),
        childColumns = arrayOf("id_"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["username", "email", "country_name"])]
)
data class UserEntity(
    @ColumnInfo(name = "id") @SerializedName("id_")
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "username") @SerializedName("username")
    val username: String,
    @ColumnInfo(name = "password") @SerializedName("password")
    var password: String,
    @ColumnInfo(name = "email") @SerializedName("email")
    var email: String,
    @ColumnInfo(name = "birthday") @SerializedName("birthday")
    var birthday: Date,
    @Embedded
    @ColumnInfo(name = "country") @SerializedName("country")
    var country: CountryEntity,
    @ColumnInfo(name = "leaderBoard_id") @SerializedName("leaderBoard_id")
    var leaderBoardId: UUID,
    @ColumnInfo(name = "profile_image") @SerializedName("profile_image")
    var profileImage: Uri,
    @Embedded
    @ColumnInfo(name = "user_status") @SerializedName("user_status")
    var status: UserProfileState
)