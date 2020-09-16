package com.example.android_todo.ui.todoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_todo.R
import com.example.android_todo.data.Result
import com.example.android_todo.databinding.FragmentTodoListBinding
import com.example.android_todo.di.Injectable
import com.example.android_todo.extension.navigateTo
import com.example.android_todo.ui.BaseFragment
import javax.inject.Inject

class TodoListFragment : BaseFragment(), Injectable {

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

        val searchMenuItem = binding.bottomAppBar.menu.findItem(R.id.search_action)
        val searchView = (searchMenuItem.actionView as SearchView)
        searchMenuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.floatingBtn.hide()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.floatingBtn.show()
                searchView.clearFocus()
                return true
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchTodoList(newText ?: "")
                return true
            }
        })

        with(binding.todoList) {
            layoutManager = LinearLayoutManager(context)
            adapter = TodoListAdapter { todo ->
                searchView.clearFocus()
                searchMenuItem.collapseActionView()
                navigateTo(TodoListFragmentDirections.showTodoEditFragment(todo))
            }
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    with(binding.floatingBtn) {
                        if (newState == RecyclerView.SCROLL_STATE_DRAGGING || searchMenuItem.isActionViewExpanded) hide() else show()
                    }
                }
            })
        }

        viewModel.getTodoList()
        viewModel.todoList.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showProgressBar()
                is Result.Success -> {
                    hideProgressBar()
                    (binding.todoList.adapter as TodoListAdapter).data = result.value!!
                }
                is Result.Failed -> {
                    hideProgressBar()
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.floatingBtn.setOnClickListener {
            navigateTo(TodoListFragmentDirections.showTodoEditFragment())
        }

        return binding.root
    }
}
