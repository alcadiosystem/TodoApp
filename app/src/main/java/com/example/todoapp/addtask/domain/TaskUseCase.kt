package com.example.todoapp.addtask.domain

import com.example.todoapp.addtask.data.TaskRepository
import com.example.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.tasks
}

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task:TaskModel){
        taskRepository.add(task)
    }
}

class UpdateTaskUSeCase @Inject constructor(private val taskRepository: TaskRepository){
    suspend operator fun invoke(task:TaskModel){
        taskRepository.update(task)
    }
}