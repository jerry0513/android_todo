package com.example.android_todo.ui.todoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.android_todo.R
import com.example.android_todo.databinding.FragmentTodoDetailBinding
import com.example.android_todo.di.Injectable
import com.example.android_todo.ui.BaseFragment
import javax.inject.Inject

class TodoDetailFragment : BaseFragment(), Injectable {

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

        binding.todo = args.todo

        return binding.root
    }
}