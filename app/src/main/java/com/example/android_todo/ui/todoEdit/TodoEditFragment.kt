package com.example.android_todo.ui.todoEdit

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android_todo.R
import com.example.android_todo.bindingAdapter.setDate
import com.example.android_todo.bindingAdapter.setTime
import com.example.android_todo.data.Result
import com.example.android_todo.databinding.FragmentTodoEditBinding
import com.example.android_todo.di.Injectable
import com.example.android_todo.ui.BaseFragment
import com.google.android.material.datepicker.MaterialDatePicker
import org.threeten.bp.*
import javax.inject.Inject

class TodoEditFragment : BaseFragment(), Injectable {

    @Inject
    lateinit var todoEditViewModel: TodoEditViewModel

    private val args: TodoEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodoEditBinding.inflate(inflater)

        args.todo?.let { todo ->
            todoEditViewModel.setTodo(todo)
            binding.title.setText(todo.title)
            binding.description.setText(todo.description)
        }

        binding.title.addTextChangedListener {
            todoEditViewModel.title.value = "$it"
        }

        binding.description.addTextChangedListener {
            todoEditViewModel.description.value = "$it"
        }

        binding.date.setOnClickListener {
            MaterialDatePicker.Builder
                .datePicker()
                .build().apply {
                    addOnPositiveButtonClickListener {
                        todoEditViewModel.date.value = LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(it),
                            ZoneId.systemDefault()
                        ).toLocalDate()
                    }
                }.show(parentFragmentManager, "date")
        }

        binding.time.setOnClickListener {
            TimePickerDialog(
                context,
                { _, h, m ->
                    todoEditViewModel.time.value = LocalTime.of(h, m)
                },
                10, 10, true
            ).show()
        }

        with(binding.bottomAppBar) {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.edit_action -> todoEditViewModel.editTodo()
                    R.id.delete_action -> todoEditViewModel.deleteTodo()
                }
                true
            }
        }

        todoEditViewModel.dateTime.observe(viewLifecycleOwner) {
            binding.date.setDate(it)
            binding.time.setTime(it)
        }

        todoEditViewModel.enabledDelete.observe(viewLifecycleOwner) {
            binding.bottomAppBar.menu.findItem(R.id.delete_action).isVisible = it
        }

        todoEditViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> showProgressBar()
                is Result.Success -> {
                    hideProgressBar()
                    findNavController().popBackStack()
                }
                is Result.Failed -> {
                    hideProgressBar()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }
}
