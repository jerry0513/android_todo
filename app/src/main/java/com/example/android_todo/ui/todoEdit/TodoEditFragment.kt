package com.example.android_todo.ui.todoEdit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.android_todo.R
import com.example.android_todo.data.Result
import com.example.android_todo.databinding.FragmentTodoEditBinding
import com.example.android_todo.di.Injectable
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
            viewModel = todoEditViewModel
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.bottomAppBar.setOnMenuItemClickListener {
            todoEditViewModel.addTodo()
            true
        }

        todoEditViewModel.status.observe(viewLifecycleOwner) {
            when(it) {
                is Result.Success -> {
                    findNavController().popBackStack()
                }
                is Result.Failed -> { }
            }
        }

        return binding.root
    }
}