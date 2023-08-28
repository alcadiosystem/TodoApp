package com.example.todoapp.addtask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity (
    @PrimaryKey
    val id: Int,
    val task: String,
    var selected: Boolean = false
)