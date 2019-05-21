/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "LeaderBoard")
data class LeaderBoardEntity(
    @PrimaryKey @ColumnInfo(name = "leaderBoard_id") @SerializedName("id_")
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "points") @SerializedName("points")
    var points: Long = 0,
    @ColumnInfo(name = "challenges_participated") @SerializedName("challenges_participated")
    var num_of_participates: Long = 0,
    @ColumnInfo(name = "challenges_completed") @SerializedName("challenges_completed")
    var completed_challenges: Long = 0
) : Parcelable