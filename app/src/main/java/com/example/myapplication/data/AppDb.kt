package com.example.myapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.TaskDao
import com.example.myapplication.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}