package com.example.todoapp.addtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    //DAO
    abstract fun taskDao(): TaskDAO
}