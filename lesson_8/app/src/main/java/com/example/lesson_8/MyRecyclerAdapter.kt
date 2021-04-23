package com.example.lesson_8

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_8.Room.NoteEntity

class MyRecyclerAdapter internal constructor(context: Context?, data: List<NoteEntity?>?) :
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    private val context = context!!
    private val mData: List<NoteEntity?>?
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private var gClickListener: ItemLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(
            R.layout.note_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData!![position]
        if (item!!.title != null) holder.title.text = item.title
        else holder.title.visibility = GONE
        holder.text.text = item.text
        if (item.color == context.getString(R.string.white)) {
            holder.title.setTextColor(context.resources.getColor(R.color.black_87))
            holder.text.setTextColor(context.resources.getColor(R.color.warm_grey_three))
        } else {
            holder.title.setTextColor(context.resources.getColor(R.color.white))
            holder.text.setTextColor(context.resources.getColor(R.color.white))
        }
        holder.back.setBackgroundColor(getColor(context, MapOfColors().getColor(item.color!!)))
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        var title: TextView
        var text: TextView
        var back: LinearLayout
        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        override fun onLongClick(view: View?): Boolean {
            if (gClickListener != null) gClickListener!!.onItemLongClick(view, adapterPosition)
            return true
        }

        init {
            title = itemView.findViewById(R.id.title)
            text = itemView.findViewById(R.id.text)
            back = itemView.findViewById(R.id.back)
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

    }

    fun getItem(id: Int): NoteEntity? {
        return mData!![id]
    }

    fun setLongClickListener(itemLongClickListener: ItemLongClickListener?) {
        gClickListener = itemLongClickListener
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemLongClickListener {
        fun onItemLongClick(view: View?, position: Int)
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}
