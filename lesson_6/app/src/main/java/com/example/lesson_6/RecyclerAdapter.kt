package com.example.lesson_6

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (_items: List<RecyclerItem>) : RecyclerView.Adapter<ItemsHolder>() {

    val items: List<RecyclerItem> = _items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ItemsHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int = items.count()
}

class ItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameText = itemView.findViewById<TextView>(R.id.itemName)
    private val numberText = itemView.findViewById<TextView>(R.id.itemNumber)
    private val commentaryText = itemView.findViewById<TextView>(R.id.commentary)

    fun bind(item: RecyclerItem) {
        nameText.text = item.itemName
        numberText.text = item.itemNumber.toString()
        if (item.isRedAlert) {
            commentaryText.setTextColor(ContextCompat.getColor(itemView.context, R.color.coral))
        }
        else {
            commentaryText.setTextColor(ContextCompat.getColor(itemView.context, R.color.charcoal_grey))
            itemView.findViewById<ImageView>(R.id.imageAlert).visibility = GONE
        }
        commentaryText.text = item.commentary
    }
}