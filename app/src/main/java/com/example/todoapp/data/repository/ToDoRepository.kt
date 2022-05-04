package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.ToDoDao
import com.example.todoapp.data.model.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insert(toDoData: ToDoData) = toDoDao.insertData(toDoData = toDoData)

    suspend fun update(toDoData: ToDoData) = toDoDao.updateData(toDoData = toDoData)

    suspend fun delete(toDoData: ToDoData) = toDoDao.deleteData(toDoInt = toDoData)

    suspend fun deleteAll() = toDoDao.deleteAllData()

    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return toDoDao.searchDatabase(searchQuery)
    }
}