package com.example.lesson_10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_10.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: RecyclerViewAdapter
    lateinit var mService: GetItemsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val recycler: RecyclerView = binding.recycler
        mService = Common.retrofitService
        recycler.layoutManager = GridLayoutManager(this, 1)
        downloadText(recycler)
        binding.buttonToMap.setOnClickListener { openMap() }
    }

    private fun downloadText(recycler: RecyclerView) {
        mService.getAllItems().enqueue(object : Callback<MutableList<BridgeInfoItem>> {
            override fun onFailure(call: Call<MutableList<BridgeInfoItem>>, t: Throwable) {}

            override fun onResponse(
                call: Call<MutableList<BridgeInfoItem>>,
                response: Response<MutableList<BridgeInfoItem>>
            ) {
                mainAdapter =
                    RecyclerViewAdapter(baseContext, response.body() as MutableList<BridgeInfoItem>)
                mainAdapter.notifyDataSetChanged()
                recycler.adapter = mainAdapter
            }
        }
        )
    }

    private fun openMap() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}