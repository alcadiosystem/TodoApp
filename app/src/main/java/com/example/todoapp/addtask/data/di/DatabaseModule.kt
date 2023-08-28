package com.example.todoapp.addtask.data.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.addtask.data.TaskDAO
import com.example.todoapp.addtask.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providerTodoDatabase(@ApplicationContext appContext: Context): TaskDatabase {
        return Room.databaseBuilder(appContext, TaskDatabase::class.java, "TaskDatabase").build()
    }

    @Provides
    fun provideTaskDao(taskDatabase:TaskDatabase):TaskDAO{
        return taskDatabase.taskDao()
    }
}