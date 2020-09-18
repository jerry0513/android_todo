package com.example.android_todo.ui.todoList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_todo.bindingAdapter.setDateTime
import com.example.android_todo.data.TodoEntity
import com.example.android_todo.databinding.ListItemTodoBinding

class TodoListAdapter(private val onItemClickListener: (TodoEntity) -> Unit) :
    RecyclerView.Adapter<TodoListViewHolder>() {

    var data = listOf<TodoEntity>()
        set(value) {
            val diffCallback = TodoDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder =
        TodoListViewHolder.create(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) =
        holder.bind(data[position], onItemClickListener)
}

class TodoListViewHolder(val binding: ListItemTodoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    inline fun bind(item: TodoEntity, crossinline onItemClickListener: (TodoEntity) -> Unit) {
        with(binding) {
            title.text = item.title
            description.text = item.description
            eventTime.setDateTime(item.eventTime)
            root.setOnClickListener {
                onItemClickListener.invoke(item)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): TodoListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemTodoBinding.inflate(inflater, parent, false)
            return TodoListViewHolder(binding)
        }
    }
}

class TodoDiffCallback(
    private val oldList: List<TodoEntity>,
    private val newList: List<TodoEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.description == newItem.description && oldItem.eventTime == newItem.eventTime
    }
}
