package com.example.lesson_8

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration(
    private val left: Int = R.dimen.decoration_space,
    private val right: Int = R.dimen.decoration_space,
    private val top: Int = R.dimen.decoration_space,
    private val bottom: Int = R.dimen.decoration_space
) : ItemDecoration() {
    constructor(offset: Int) : this(offset, offset, offset, offset)

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(left, top, right, bottom)
    }
}