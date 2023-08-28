package com.example.todoapp.addtask.data

import com.example.todoapp.addtask.data.local.TaskDAO
import com.example.todoapp.addtask.data.local.TaskEntity
import com.example.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDAO) {

    val tasks: Flow<List<TaskModel>> =
        taskDao.getTasks().map { items -> items.map { TaskModel(it.id, it.task, it.selected) } }

    suspend fun add(task: TaskModel) {
        taskDao.addTask(TaskEntity(task.id, task.task, task.selected))
    }


}