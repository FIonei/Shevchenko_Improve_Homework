package com.example.lesson_6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class ItemThreeFragment : Fragment() {
    private lateinit var button: Button
    private lateinit var pager: ViewPager2
    private val list: List<PagerItem> = PagerRepository().items
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button = view.findViewById(R.id.button)
        pager = view.findViewById(R.id.view_pager)
        button.setOnClickListener { showingButton() }
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.adapter = ViewPagerAdapter(this.context, list)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showingButton() {
        if (pager.visibility == VISIBLE) {
            pager.visibility = GONE
            button.text = getString(R.string.show)
        }
        else {
            pager.visibility = VISIBLE
            button.text = getString(R.string.hide)
        }
    }



    companion object {
        fun newInstance(): ItemThreeFragment = ItemThreeFragment()
    }
}