package com.example.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.ToDoDatabase
import com.example.todoapp.data.model.ToDoData
import com.example.todoapp.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getInstance(application).toDoDao()
    private val repository: ToDoRepository = ToDoRepository(toDoDao)

    private val getAllData: LiveData<List<ToDoData>> = repository.getAllData

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(toDoData)
        }
    }

}