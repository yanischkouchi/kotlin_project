package com.example.myapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task ORDER BY id DESC")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)
}

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
