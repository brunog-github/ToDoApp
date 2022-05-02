package com.example.todoapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp.util.Constants.TABLE_NAME
import com.example.todoapp.util.Priority
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val priority: Priority,
    val description: String
): Parcelable
