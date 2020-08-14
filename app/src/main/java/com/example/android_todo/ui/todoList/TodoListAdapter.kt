package com.example.android_todo.ui.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_todo.R
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.databinding.ListItemTodoBinding

class TodoListAdapter(private val onItemClickListener: (TodoEntity) -> Unit) :
    RecyclerView.Adapter<TodoListViewHolder>() {

    var data = listOf<TodoEntity>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder =
        TodoListViewHolder.create(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) =
        holder.bind(data[position], onItemClickListener)
}

class TodoListViewHolder(private val binding: ListItemTodoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TodoEntity, onItemClickListener: (TodoEntity) -> Unit) {
        with(binding) {
            todo = item
            root.setOnClickListener {
                onItemClickListener.invoke(item)
            }
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): TodoListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ListItemTodoBinding>(
                inflater,
                R.layout.list_item_todo,
                parent,
                false
            )
            return TodoListViewHolder(binding)
        }
    }
}