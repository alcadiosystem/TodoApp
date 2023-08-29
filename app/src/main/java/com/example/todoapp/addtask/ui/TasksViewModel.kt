package com.example.todoapp.addtask.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.addtask.domain.AddTaskUseCase
import com.example.todoapp.addtask.domain.GetTasksUseCase
import com.example.todoapp.addtask.ui.TasksUiState.*
import com.example.todoapp.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    getTasksUseCase: GetTasksUseCase
): ViewModel() {

    val uiState:StateFlow<TasksUiState> = getTasksUseCase().map (::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

//    private val _tasks = mutableStateListOf<TaskModel>()
//    val tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(task: TaskModel) {
        //Actualizar check
//        val index = _tasks.indexOf(task)
//        _tasks[index] = _tasks[index].let {
//            it.copy(selected = !it.selected)
//        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        //Borrar item
//        val task = _tasks.find { it.id == taskModel.id }
//        _tasks.remove(task)
    }
}
