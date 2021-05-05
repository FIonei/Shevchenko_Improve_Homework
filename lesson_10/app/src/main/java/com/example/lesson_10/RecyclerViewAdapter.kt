package com.example.lesson_10

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter internal constructor(context: Context?, _items: List<BridgeInfoItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val items: List<BridgeInfoItem>
    private val context: Context
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    private val timeEx: String = "bridgeTime"
    private val idEx: String = "bridgeId"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = mInflater.inflate(
            R.layout.recycler_item,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { v ->
            val context = v.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(idEx, item.id!!.toLong())
            intent.putExtra(timeEx, Time(context).uniteTime(item))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var bridgeName: TextView
        var bridgeTime: TextView
        var bridgeImage: ImageView
        override fun onClick(view: View?) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            bridgeImage = itemView.findViewById(R.id.bridgeImage)
            bridgeName = itemView.findViewById(R.id.bridgeName)
            bridgeTime = itemView.findViewById(R.id.bridgeTime)
            itemView.setOnClickListener(this)
        }

        fun bind(item: BridgeInfoItem) {
            bridgeName.text = item.bridgeName
            val str = Time(context).uniteTime(item)
            bridgeTime.text = str
            Time(context).setCurrentBridgeImage(
                bridgeImage,
                item.divorces!![0].start!!,
                item.divorces[0].end!!
            )
        }
    }

    fun getItem(id: Int): BridgeInfoItem {
        return items[id]
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        this.context = context!!
        mInflater = LayoutInflater.from(context)
        items = _items
    }

}