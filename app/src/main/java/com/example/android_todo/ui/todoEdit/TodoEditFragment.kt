package com.example.android_todo.ui.todoEdit

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.android_todo.R
import com.example.android_todo.data.Result
import com.example.android_todo.databinding.FragmentTodoEditBinding
import com.example.android_todo.di.Injectable
import com.google.android.material.datepicker.MaterialDatePicker
import javax.inject.Inject

class TodoEditFragment : Fragment(), Injectable {

    @Inject
    lateinit var todoEditViewModel: TodoEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTodoEditBinding>(
            inflater,
            R.layout.fragment_todo_edit,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = todoEditViewModel
        }

        binding.date.setOnClickListener {
            MaterialDatePicker.Builder
                .datePicker()
                .build().apply {
                    addOnPositiveButtonClickListener {
                        todoEditViewModel.date.value = it
                    }
                }.show(parentFragmentManager, "date")
        }

        binding.time.setOnClickListener {
            TimePickerDialog(
                context,
                { _, h, m ->
                    todoEditViewModel.time.value = h * 60 + m
                },
                10, 10, true
            ).show()
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.bottomAppBar.setOnMenuItemClickListener {
            todoEditViewModel.addTodo()
            true
        }

        todoEditViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> binding.progressBar.show()
                is Result.Success -> {
                    binding.progressBar.hide()
                    findNavController().popBackStack()
                }
                is Result.Failed -> {
                    binding.progressBar.hide()
                }
            }
        }

        return binding.root
    }
}
