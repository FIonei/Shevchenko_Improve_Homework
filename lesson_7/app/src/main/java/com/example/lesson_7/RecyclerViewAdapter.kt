package com.example.lesson_7

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class RecyclerViewAdapter internal constructor(context: Context?, _items: List<BridgeInfoItem>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val items: List<BridgeInfoItem>
    private val context: Context
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private var imageList: MutableList<Int> = mutableListOf()
    private var time: MutableList<String> = mutableListOf()

    private val imageEx: String = "image"
    private val nameEx: String = "bridgeName"
    private val timeEx: String = "bridgeTime"
    private val commentEx: String = "bridgeCommentary"


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
            intent.putExtra(imageEx, imageList[position])
            intent.putExtra(nameEx, item.bridgeName)
            intent.putExtra(timeEx, time[position])
            intent.putExtra(commentEx, item.description)
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
            var str = ""
            for (i in item.divorces!!) {
                str += (itemView.context.getString(R.string.time_format, i.start, i.end) + " ")
            }
            bridgeTime.text = str
            time.add(str)
            setCurrentBridgeImage(bridgeImage, item.divorces[0].start!!, item.divorces[0].end!!)
        }

        private fun setCurrentBridgeImage(bridgeImage: ImageView, start: String, end: String) {
            val _pattern = context.getString(R.string.pattern_of_time)
            val pattern = SimpleDateFormat(_pattern, Locale.ENGLISH)
            val time1 = timeToIntMinutes(pattern.format(Date()))
            val time2start = timeToIntMinutes(start)
            val time2end = timeToIntMinutes(end)
            val imageId: Int
            if (time2start - time1 <= 60) {
                if (time2start - time1 >= 0) imageId = R.drawable.ic_brige_soon
                else if (time2end - time1 >= 0) imageId = R.drawable.ic_brige_late
                else imageId = R.drawable.ic_brige_normal
            } else imageId = R.drawable.ic_brige_normal
            bridgeImage.setImageDrawable(getDrawable(context, imageId))
            imageList.add(imageId)
        }

        private fun timeToIntMinutes(s: String): Int {
            val separated = s.split(':')
            return (separated[0].toInt() * 60 + separated[1].toInt())
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