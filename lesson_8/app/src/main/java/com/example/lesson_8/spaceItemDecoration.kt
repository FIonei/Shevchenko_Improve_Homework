package com.example.lesson_8

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceItemDecoration(
    private val left: Int,
    private val right: Int,
    private val top: Int,
    private val bottom: Int
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