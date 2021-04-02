package com.example.lesson_4

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ItemClickListener,
    BaseRecyclerViewAdapter.ItemClickListener {
    val DetailInfoItem = listOf(
        mapOf("Квитанции" to "- 40 080,55 Р"),
        mapOf("Счетчики" to "Подайте показания"),
        mapOf("Рассрочка" to "Сл. платеж 25.02.2018"),
        mapOf("Страхование" to "Полис до 12.01.2019"),
        mapOf("Интернет и ТВ" to "Баланс 350 Р"),
        mapOf("Домофон" to "Подключен"),
        mapOf("Охрана" to "Нет")
    )
    val BaseInfoItem = listOf("Контакты УК и служб", "Мои заявки", "Памятка жителя А101")
    private lateinit var binding: ActivityMainBinding
    lateinit var baseAdapter: BaseRecyclerViewAdapter
    lateinit var detailAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.mainToolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)
        binding.mainToolbar.setNavigationOnClickListener { doBack() }
        val detailItems: RecyclerView = binding.recyclerDetail
        val baseItems: RecyclerView = binding.recyclerBase
        val gridLayoutManager = GridLayoutManager(this, 2)

        //хз почему, но тут если элемент последний, то столбец должен быть один, но тут работает наоборот
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == DetailInfoItem.count() - 1) 2
                else 1
            }
        }
        detailItems.layoutManager = gridLayoutManager
        baseItems.layoutManager = GridLayoutManager(this, 1)
        detailAdapter = RecyclerViewAdapter(this, DetailInfoItem)
        detailAdapter.setClickListener(this)
        baseAdapter = BaseRecyclerViewAdapter(this, BaseInfoItem)
        baseAdapter.setClickListener(this)
        binding.recyclerDetail.adapter = detailAdapter
        binding.recyclerBase.adapter = baseAdapter
        val space: Int = 12
        binding.recyclerBase.addItemDecoration(SpaceItemDecoration(space))
        binding.recyclerDetail.addItemDecoration(SpaceItemDecoration(space))
    }

    override fun onItemClick(view: View?, position: Int) {
        val type = view.toString().substringAfterLast("id/").dropLast(1)
        var text = "Выбран элемент: "
        if (type == "detail") text += detailAdapter.getItem(position).keys.toString().drop(1)
            .dropLast(1)
        else text += baseAdapter.getItem(position)
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun doBack() {
        this.finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

    //почему-то при нажатии на кнопки меню эта функция не вызывается
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_info -> {
                doInfo()
                return true
            }
            R.id.toolbar_toast -> {
                doToast()
                return true
            }
            else -> return false
        }
    }

    private fun doToast() {
        Toast.makeText(this, "Тост домику", Toast.LENGTH_SHORT).show()
    }

    private fun doInfo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Кнопка с информацией")
            .setMessage("Информация пока не добавлена")
            .setPositiveButton("Закрыть окно") { dialog, id ->
                dialog.cancel()
            }
        builder.create()
    }
}