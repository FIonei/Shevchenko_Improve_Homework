package com.example.lesson_6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView


class ViewPagerAdapter(_context: Context?, _list: List<PagerItem>) : RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {
    private val context: Context
    private val list: List<PagerItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.view_pager_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.image.setImageDrawable(getDrawable(context, list[position].image))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val image: ImageView

        init {
            name = itemView.findViewById(R.id.imageName)
            image = itemView.findViewById(R.id.imageView)
        }
    }

    init {
        this.context = _context!!
        this.list = _list
    }
}