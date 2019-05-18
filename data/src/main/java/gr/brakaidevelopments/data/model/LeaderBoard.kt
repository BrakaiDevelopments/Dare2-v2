/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "LeaderBoard")
data class LeaderBoard(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id_")
    val id: UUID = UUID.randomUUID(),
    @PrimaryKey @ColumnInfo(name = "points") @SerializedName("points")
    val points: Long,
    @PrimaryKey @ColumnInfo(name = "challenges_participated") @SerializedName("challenges_participated")
    val num_of_participates: Long,
    @PrimaryKey @ColumnInfo(name = "challenges_completed") @SerializedName("challenges_completed")
    val completed_challenges: Long
)