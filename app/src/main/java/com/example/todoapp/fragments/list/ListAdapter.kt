package com.example.todoapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.model.ToDoData
import com.example.todoapp.databinding.RowLayoutBinding
import com.example.todoapp.util.Priority

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    inner class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(dataList[position]) {
                binding.textViewTitle.text = title
                binding.textViewDescription.text = description
                binding.rowBackground.setOnClickListener {
                    val toDoItem = ToDoData(
                        id = id,
                        title = title,
                        priority = priority,
                        description = description
                    )
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(toDoItem)
                    itemView.findNavController().navigate(action)
                }

                when (priority) {
                    Priority.HIGH -> {
                        binding.priorityIndicator
                            .setCardBackgroundColor(
                                ContextCompat.getColor(itemView.context, R.color.red)
                            )
                    }
                    Priority.MEDIUM -> {
                        binding.priorityIndicator
                            .setCardBackgroundColor(
                                ContextCompat.getColor(itemView.context, R.color.yellow)
                            )
                    }
                    else -> {
                        binding.priorityIndicator
                            .setCardBackgroundColor(
                                ContextCompat.getColor(itemView.context, R.color.green)
                            )
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    fun setData(toDoData: List<ToDoData>) {
        this.dataList = toDoData
        notifyDataSetChanged()
    }
}