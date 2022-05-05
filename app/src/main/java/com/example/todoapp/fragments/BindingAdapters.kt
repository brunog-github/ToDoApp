package com.example.todoapp.fragments

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.model.ToDoData
import com.example.todoapp.fragments.list.ListFragmentDirections
import com.example.todoapp.util.Priority
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {
    companion object {

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
            view.setOnClickListener {
                if(navigate) {
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabase: LiveData<Boolean>) {
            when(emptyDatabase.value) {
                true -> view.visibility = View.VISIBLE
                else -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority){
             when(priority) {
                Priority.LOW -> view.setSelection(2)
                Priority.MEDIUM -> view.setSelection(1)
                Priority.HIGH-> view.setSelection(0)
            }
        }

        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(cardView: CardView, priority: Priority) {
            when(priority) {
                Priority.HIGH -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.red))
                }
                Priority.MEDIUM -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
                }
                Priority.LOW -> {
                    cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
                }
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: ToDoData) {
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
    }
}