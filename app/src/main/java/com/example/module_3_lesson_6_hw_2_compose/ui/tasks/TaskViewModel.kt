package com.example.module_3_lesson_6_hw_2_compose.ui.tasks

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.module_3_lesson_6_hw_2_compose.data.tasks.TaskItem
import com.example.module_3_lesson_6_hw_2_compose.data.tasks.TaskRepository
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.module_3_lesson_6_hw_2_compose.data.settings.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository,
    private val settingsRepository: SettingsRepository
    ) : ViewModel() {

    val taskListUiState: StateFlow<TaskListUiState> =
        taskRepository.getAllTasksByIdStream().map { TaskListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = TaskListUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var taskAddItemUiState by mutableStateOf(TaskItemUiState())
        private set

    val tasksNumber = mutableStateOf(0)

    private fun getTaskNumber() {
        viewModelScope.launch {
            tasksNumber.value = settingsRepository.getSettingByName("TasksNumber").value
        }
    }

    init {
        getTaskNumber()
    }



    fun updateTaskUiState(taskItemDetails: TaskItemDetails) {
        taskAddItemUiState = TaskItemUiState(taskItemDetails = taskItemDetails)
    }

    suspend fun saveTask() {
        taskRepository.insert(taskAddItemUiState.taskItemDetails.toTaskItem())
        Log.d("MYLOG", "task added: ${taskAddItemUiState.taskItemDetails.toTaskItem()}")
    }

    suspend fun deleteAll() {
        taskRepository.deleteAll()
    }

    suspend fun changeTasksNumberUp() {
        val settingsItemTasksNumber = settingsRepository.getSettingByName("TasksNumber")
        val updatedTasksNumber = settingsItemTasksNumber.value + 5
        val updatedSettingsItemTasksNumber = settingsItemTasksNumber.copy(value = updatedTasksNumber)
        settingsRepository.update(updatedSettingsItemTasksNumber)
        tasksNumber.value = updatedTasksNumber
    }
    suspend fun changeTasksNumberDown() {
        val settingsItemTasksNumber = settingsRepository.getSettingByName("TasksNumber")
        if (settingsItemTasksNumber.value >= 10) {
            val updatedTasksNumber = settingsItemTasksNumber.value - 5
            val updatedSettingsItemTasksNumber = settingsItemTasksNumber.copy(value = updatedTasksNumber)
            settingsRepository.update(updatedSettingsItemTasksNumber)
            tasksNumber.value = updatedTasksNumber
        }
    }

    fun checkTaskMaximumNumber(): Boolean {
        return taskListUiState.value.taskList.size < tasksNumber.value
    }
}

data class TaskListUiState(
    val taskList: List<TaskItem> = listOf()
)

data class TaskItemDetails(
    val id: Int = 0,
    val taskTitle: String = "",
    val taskText: String = ""
)

data class TaskItemUiState(
    val taskItemDetails: TaskItemDetails = TaskItemDetails()
)



fun TaskItemDetails.toTaskItem(): TaskItem = TaskItem(
    id = id,
    taskTitle = taskTitle,
    taskText = taskText
)

fun TaskItem.toTaskItemDetails(): TaskItemDetails = TaskItemDetails(
    id = id,
    taskTitle = taskTitle,
    taskText = taskText
)

fun TaskItem.toTaskItemUiState(): TaskItemUiState = TaskItemUiState(
    taskItemDetails = this.toTaskItemDetails()
)