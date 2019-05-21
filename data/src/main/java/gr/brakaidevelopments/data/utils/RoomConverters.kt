/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.utils

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import gr.brakaidevelopments.data.model.ChallengeState
import gr.brakaidevelopments.data.model.UserProfileState
import gr.brakaidevelopments.domain.utils.fromJson
import java.util.*


object RoomConverters {
    @JvmStatic
    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun uuidToJson(uuid: UUID): String {
        return gson.toJson(uuid)
    }

    @JvmStatic
    @TypeConverter
    fun uuidFromJson(uuid: String): UUID {
        return gson.fromJson(uuid)
    }

    @JvmStatic
    @TypeConverter
    fun dateToJson(date: Date): String {
        return gson.toJson(date)
    }

    @JvmStatic
    @TypeConverter
    fun dateFromJson(date: String): Date {
        return gson.fromJson(date)
    }

    @JvmStatic
    @TypeConverter
    fun uriToJson(uri: Uri): String {
        return uri.toString()
    }

    @JvmStatic
    @TypeConverter
    fun uriFromJson(uri: String): Uri {
        return Uri.parse(uri)
    }

    @JvmStatic
    @TypeConverter
    fun userStatusToJson(userStatus: UserProfileState): String {
        return userStatus.toString()
    }

    @JvmStatic
    @TypeConverter
    fun userStatusFromJson(userStatus: String): UserProfileState {
        return UserProfileState.valueOf(userStatus)
    }

    @JvmStatic
    @TypeConverter
    fun challengeStatusToJson(challengeState: ChallengeState): String {
        return challengeState.toString()
    }

    @JvmStatic
    @TypeConverter
    fun challengeStatusFromJson(challengeState: String): ChallengeState {
        return ChallengeState.valueOf(challengeState)
    }


}