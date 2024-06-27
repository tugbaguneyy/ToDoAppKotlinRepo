package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.entity.ToDos

@Dao
interface ToDosDao {
    @Query("SELECT * FROM toDos")
    suspend fun loadToDos() : List<ToDos>

    @Insert
    suspend fun save(toDos: ToDos)

    @Update
    suspend fun update(toDos: ToDos)

    @Delete
    suspend fun delete(toDos: ToDos)

    @Query("SELECT * FROM toDos WHERE name like '%' || :searchText || '%'")
    suspend fun search(searchText:String) : List<ToDos>
}