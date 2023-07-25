package com.example.module_3_lesson_6_hw_2_compose.data.tasks

import kotlinx.coroutines.flow.Flow

class OfflineTaskRepository(private val taskDao: TaskDao) : TaskRepository {
    override suspend fun insert(taskItem: TaskItem) = taskDao.insert(taskItem)

    override suspend fun update(taskItem: TaskItem) = taskDao.update(taskItem)

    override suspend fun delete(taskItem: TaskItem) = taskDao.delete(taskItem)

    override fun getAllTasksByIdStream(): Flow<List<TaskItem>> = taskDao.getAllTasksById()
    override suspend fun deleteAll() = taskDao.deleteAll()
    override fun getAllTasks(): List<TaskItem> = taskDao.getAllTasks()
}