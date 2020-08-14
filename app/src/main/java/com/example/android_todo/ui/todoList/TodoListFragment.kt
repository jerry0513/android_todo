package com.example.android_todo.ui.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_todo.R
import com.example.android_todo.data.Result
import com.example.android_todo.databinding.FragmentTodoListBinding
import com.example.android_todo.di.Injectable
import com.example.android_todo.extension.navigateTo
import javax.inject.Inject

class TodoListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTodoListBinding>(
            inflater,
            R.layout.fragment_todo_list,
            container,
            false
        )

        with(binding.todoList) {
            layoutManager = LinearLayoutManager(context)
            adapter = TodoListAdapter { todo ->
                navigateTo(TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment(todo))
            }
        }

        viewModel.getTodoList()
        viewModel.todoList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    (binding.todoList.adapter as TodoListAdapter).data = result.value
                }
                is Result.Failed -> {}
            }
        }

        binding.floatingBtn.setOnClickListener {
            navigateTo(TodoListFragmentDirections.actionTodoListFragmentToTodoEditFragment())
        }

        return binding.root
    }
}
