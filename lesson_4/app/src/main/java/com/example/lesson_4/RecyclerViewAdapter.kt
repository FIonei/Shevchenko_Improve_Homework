package com.example.lesson_4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter internal constructor(context: Context?, data: List<InfoItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val ITEM_SINGLE = 0
    private val ITEM_MULTIPLE = 1
    private val mData: List<InfoItem>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        view =
            if (viewType == ITEM_MULTIPLE) mInflater.inflate(
                R.layout.detail_info_item,
                parent,
                false
            )
            else mInflater.inflate(R.layout.base_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        if (getItemViewType(position) == ITEM_SINGLE) {
            holder.firstTextView.text = item.firstName
            holder.secondTextView.text = item.secondName ?: ""
            holder.image.setImageResource(item.image)
        } else {
            holder.firstTextView.text = item.firstName
            holder.secondTextView.text = item.secondName ?: ""
            holder.image.setImageResource(item.image)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var firstTextView: TextView
        var secondTextView: TextView
        var image: ImageView
        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            firstTextView = itemView.findViewById(R.id.first_name)
            secondTextView = itemView.findViewById(R.id.second_name)
            image = itemView.findViewById(R.id.image)
            itemView.setOnClickListener(this)
        }
    }

    fun getItem(id: Int): InfoItem {
        return mData[id]
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    private fun InfoItem.isSingleInLine(position: Int): Boolean {
        if (position + 1 < itemCount) {
            if ((position % 2 == 0) && (mData[position + 1].secondName == null)) return true
            else if (this.secondName != null) return false
        }
        return true
    }

    override fun getItemViewType(position: Int): Int {
        val item = mData[position]
        return if (item.isSingleInLine(position)) {
            ITEM_SINGLE
        } else {
            ITEM_MULTIPLE
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}