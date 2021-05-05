package com.example.lesson_10

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_10.databinding.ActivityDetailBinding
import java.io.IOException
import kotlin.concurrent.thread

class DetailActivity : AppCompatActivity() {
    lateinit var mService: GetItemService
    private val idEx: String = "bridgeId"
    private val timeEx: String = "bridgeTime"
    private lateinit var thread: Thread

    private lateinit var item: BridgeInfoItem
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = null
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { finish() }

        val view = binding.root
        setContentView(view)
        val id = intent.extras!!.getLong(idEx)
        mService = Detail.retrofitService
        thread = Thread {
            try {
                item = download(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
        thread.join()
        addItems()
    }

    private fun addItems() {
        if (item.bridgeName != null) {
            binding.bridgeName.text = item.bridgeName
            binding.bridgeName.setTextColor(getResources().getColor(R.color.black))
            binding.bridgeCommentary.text = item.description
            binding.bridgeCommentary.setTextColor(getResources().getColor(R.color.black))
            setTimeAndImage()
        }
    }

    private fun setTimeAndImage() {
        if (item.divorces != null) {
            Time(applicationContext).setCurrentBridgeImage(
                binding.bridgeImage,
                item.divorces!![0].start!!,
                item.divorces!![0].end!!
            )
            binding.bridgeTime.text = Time(applicationContext).uniteTime(item)
        } else {
            val time = intent.extras!!.getString(timeEx)!!
            val start = time.substringBefore(" –")
            val end = time.substringAfter("– ").trimEnd()
            Time(applicationContext).setCurrentBridgeImage(
                binding.bridgeImage,
                start,
                end
            )
            binding.bridgeTime.text = time
        }
    }

    private fun download(id: Long): BridgeInfoItem {
        var item = BridgeInfoItem()
        try {
            val response = mService.getItem(id).execute()
            item = response.body()!!
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return item
    }
}