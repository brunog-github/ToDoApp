package com.example.todoapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.model.ToDoData
import com.example.todoapp.data.viewmodel.ToDoViewModel
import com.example.todoapp.databinding.FragmentUpdateBinding
import com.example.todoapp.fragments.SharedViewModel
import com.example.todoapp.util.Priority

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()
    private val sharedViewModel: SharedViewModel by viewModels()
    private val toDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.args = args

        binding.currentPrioritiesSpinner.onItemSelectedListener = sharedViewModel.listener

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            toDoViewModel.deleteData(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.currentItem.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _,_ -> }
        builder.setTitle("Delete '${args.currentItem.title}' ?")
        builder.setMessage("Are you sure you want to remove '${args.currentItem.title}' ?")
        builder.create().show()
    }

    private fun updateItem() {
        val title = binding.editTextCurrentTitle.text.toString()
        val description = binding.editTextCurrentDescription.text.toString()
        val getPriority = binding.currentPrioritiesSpinner.selectedItem.toString()

        val validation = sharedViewModel.verifyDataFromUser(title, description)
        if (validation) {
            val updateItem = ToDoData(
                id = args.currentItem.id,
                title = title,
                description = description,
                priority = sharedViewModel.parsePriority(getPriority)
            )
            toDoViewModel.updateData(updateItem)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}