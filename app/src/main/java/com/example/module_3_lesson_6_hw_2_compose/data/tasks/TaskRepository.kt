package com.example.module_3_lesson_6_hw_2_compose.data.tasks

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insert(taskItem: TaskItem)
    suspend fun update(taskItem: TaskItem)
    suspend fun delete(taskItem: TaskItem)
    fun getAllTasksByIdStream(): Flow<List<TaskItem>>
    suspend fun deleteAll()
    fun getAllTasks(): List<TaskItem>
}