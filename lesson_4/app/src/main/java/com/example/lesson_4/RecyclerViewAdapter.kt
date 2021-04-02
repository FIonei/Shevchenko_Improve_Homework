package com.example.lesson_4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter internal constructor(context: Context?, data: List<Map<String, String>>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val mData: List<Map<String, String>>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.detail_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        for ((key, value) in item) {
            holder.firstTextView.text = key
            holder.secondTextView.text = value
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var firstTextView: TextView
        var secondTextView: TextView
        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            firstTextView = itemView.findViewById(R.id.first_name)
            secondTextView = itemView.findViewById(R.id.second_name)
            itemView.setOnClickListener(this)
        }
    }

    fun getItem(id: Int): Map<String, String> {
        return mData[id]
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}