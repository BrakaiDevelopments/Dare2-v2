/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    suspend fun insertItem(item: T): Long

    @Insert
    suspend fun insertItems(vararg item: T): List<Long>

    @Insert
    suspend fun insertItems(item: List<T>): List<Long>

    @Update
    suspend fun updateItem(item: T): Int

    @Delete
    suspend fun deleteItem(item: T): Int

}