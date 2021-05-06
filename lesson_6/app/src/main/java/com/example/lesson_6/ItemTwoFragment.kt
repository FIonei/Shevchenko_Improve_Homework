package com.example.lesson_6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemTwoFragment : Fragment() {
    private lateinit var items: RecyclerView
    private val adapter = RecyclerAdapter(RecyclerRepository().items)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        items = view.findViewById(R.id.recycler)
        items.layoutManager = GridLayoutManager(view.context, 1)
        items.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): ItemTwoFragment = ItemTwoFragment()
    }
}