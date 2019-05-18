/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.utils

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import gr.brakaidevelopments.domain.utils.fromJson
import java.util.*


object RoomConverters {
    @JvmStatic
    private val gson = Gson()

    @JvmStatic
    @TypeConverter
    fun listToJson(uuid: UUID): String {
        return gson.toJson(uuid)
    }

    @JvmStatic
    @TypeConverter
    fun listFromJson(uuid: String): UUID {
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


}