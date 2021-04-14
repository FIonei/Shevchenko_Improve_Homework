package com.example.lesson_7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_7.databinding.ActivityMainBinding
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
}


/*для тестирования на своих объектах: name, divorces обязательны для первого экрана, description ещё и для второго
private var text = listOf(
    BridgeInfoItem(bridgeName = "Красный", divorces = listOf(Divorce(start = "20:50", end = "21:30")), description = "Текст для первого моста"),
    BridgeInfoItem(bridgeName = "Желтый", divorces = listOf(Divorce(start = "21:20", end = "21:50")), description = "Текст для второго моста"),
    BridgeInfoItem(bridgeName = "Зеленый", divorces = listOf(Divorce(start = "16:30", end = "17:00")), description = "Текст для третьего моста")
)
mainAdapter = RecyclerViewAdapter(this, text ?: listOf(BridgeInfoItem()))*/