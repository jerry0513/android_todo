package com.example.android_todo.ui.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_todo.R
import com.example.android_todo.databinding.FragmentTodoListBinding
import com.example.android_todo.di.Injectable
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
                val action = TodoListFragmentDirections.actionTodoListFragmentToTodoDetailFragment(todo.id)
                findNavController().navigate(action)
            }
        }

        (binding.todoList.adapter as TodoListAdapter).data = viewModel.getTodoList()

        return binding.root
    }
}
