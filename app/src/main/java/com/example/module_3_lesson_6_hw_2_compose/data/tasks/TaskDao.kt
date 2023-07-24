package com.example.module_3_lesson_6_hw_2_compose.data.tasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(taskItem: TaskItem)

    @Update
    suspend fun update(taskItem: TaskItem)

    @Delete
    suspend fun delete(taskItem: TaskItem)

    @Query("SELECT * from tasks ORDER BY id ASC")
    fun getAllTasksById(): Flow<List<TaskItem>>

    @Query("DELETE from tasks")
    suspend fun deleteAll()
}