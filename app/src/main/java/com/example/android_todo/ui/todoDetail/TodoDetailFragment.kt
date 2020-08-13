package com.example.android_todo.ui.todoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.android_todo.R
import com.example.android_todo.databinding.FragmentTodoDetailBinding
import com.example.android_todo.di.Injectable
import javax.inject.Inject

class TodoDetailFragment : Fragment(), Injectable {

    private val args: TodoDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: TodoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTodoDetailBinding>(
            inflater,
            R.layout.fragment_todo_detail,
            container,
            false
        )

        binding.todo = viewModel.getTodoDetail(args.todoId)

        return binding.root
    }
}