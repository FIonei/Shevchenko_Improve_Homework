package com.example.lesson_6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.lesson_6.databinding.ItemOneBinding


class ItemOneFragment : Fragment() {
    private lateinit var binding: ItemOneBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.item_one, container, false)
        return view
    }

    companion object {
        fun newInstance(): ItemOneFragment = ItemOneFragment()
    }
}