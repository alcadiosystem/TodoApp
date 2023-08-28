package com.example.todoapp.addtask.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.todoapp.addtask.ui.model.TaskModel


//@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    MyEjecutable()
}

@Composable
fun MyEjecutable() {
    Box(modifier = Modifier.fillMaxSize()) {
        AddTaskDialog(show = true, onDismiss = {}, onTaskAdded = {})
        //FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
    }
}

@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel) {
    FloatingActionButton(
        onClick = {
            tasksViewModel.onShowDialogClick()
        },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTasks by remember { mutableStateOf("") }
    if (show) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            title = {
                Text(text = "Registro de nueva tarea")
            },
            text = {
                Column(Modifier.fillMaxWidth()) {
                    TextField(
                        value = myTasks,
                        onValueChange = { myTasks = it },
                        maxLines = 1,
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { onTaskAdded(myTasks) }) {
                    Text("Añadir tarea")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDialog(onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTasks by remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Añade tu tarea",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(16.dp))
            TextField(
                value = myTasks,
                onValueChange = { myTasks = it },
                singleLine = true,
                maxLines = 1
            )
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = { onTaskAdded(myTasks) }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Añadir tarea")
            }
        }
    }
}

@Composable
fun TasksList(tasksViewModel: TasksViewModel) {
    val myTasks :List<TaskModel> = tasksViewModel.tasks
    LazyColumn {
        items(myTasks, key = {it.id}){
            ItemTask(task = it, tasksViewModel = tasksViewModel)
        }
    }
}


@Composable
fun ItemTask(task: TaskModel, tasksViewModel: TasksViewModel) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = task.task, modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            )
            Checkbox(
                checked = task.selected,
                onCheckedChange = { tasksViewModel.onCheckBoxSelected(task) })
        }
    }
}

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog: Boolean by tasksViewModel.showDialog.observeAsState(false)
    Box(modifier = Modifier.fillMaxSize()) {
        AddTaskDialog(
            show = showDialog,
            onDismiss = { tasksViewModel.onDialogClose() },
            onTaskAdded = { tasksViewModel.onTaskCreated(it) })
        FabDialog(Modifier.align(Alignment.BottomEnd), tasksViewModel)
        TasksList(tasksViewModel)
    }
}
