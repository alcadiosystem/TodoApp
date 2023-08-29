package com.example.todoapp.addtask.ui

import com.example.todoapp.addtask.ui.model.TaskModel

sealed interface TasksUiState {
    object Loading:TasksUiState
    data class Error(val throwable: Throwable):TasksUiState
    data class Success(val tasks:List<TaskModel>): TasksUiState
}