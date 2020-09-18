package com.example.android_todo.ui.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android_todo.R

class TodoItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildLayoutPosition(view) == 0)
                top = parent.resources.getDimension(R.dimen.list_item_margin_vertical).toInt()
            left = parent.resources.getDimension(R.dimen.list_item_margin_horizontal).toInt()
            right = parent.resources.getDimension(R.dimen.list_item_margin_horizontal).toInt()
            bottom = parent.resources.getDimension(R.dimen.list_item_margin_vertical).toInt()
        }
    }
}
