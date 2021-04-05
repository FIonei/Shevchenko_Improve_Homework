package com.example.lesson_4

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ItemClickListener {
    private val DetailInfoItems = listOf(
        InfoItem("Квитанции", "- 40 080,55 Р", R.drawable.ic_bill),
        InfoItem("Счетчики", "Подайте показания", R.drawable.ic_counter),
        InfoItem("Рассрочка", "Сл. платеж 25.02.2018", R.drawable.ic_installment),
        InfoItem("Страхование", "Полис до 12.01.2019", R.drawable.ic_insurance),
        InfoItem("Интернет и ТВ", "Баланс 350 Р", R.drawable.ic_tv),
        InfoItem("Домофон", "Подключен", R.drawable.ic_homephone),
        InfoItem("Охрана", "Нет", R.drawable.ic_guard)
    )
    private val BaseInfoItems = listOf(
        InfoItem("Контакты УК и служб", image = R.drawable.ic_uk_contacts),
        InfoItem("Мои заявки", image = R.drawable.ic_request),
        InfoItem("Памятка жителя А101", image = R.drawable.ic_about)
    )
    private val AllInfoItems = DetailInfoItems + BaseInfoItems
    private lateinit var binding: ActivityMainBinding
    lateinit var detailAdapter: RecyclerViewAdapter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        toolbar = binding.mainToolbar
        val view = binding.root
        setContentView(view)
        setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { doBack() }
        val detailItems: RecyclerView = binding.recyclerDetail
        val gridLayoutManager = GridLayoutManager(this, 2)

        //хз почему, но тут если элемент последний, то столбец должен быть один, но тут работает наоборот
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if ((position >= DetailInfoItems.count() - 1) && (DetailInfoItems.count() % 2 != 0)) 2
                else 1
            }
        }
        detailItems.layoutManager = gridLayoutManager
        detailAdapter = RecyclerViewAdapter(this, AllInfoItems)
        detailAdapter.setClickListener(this)
        binding.recyclerDetail.adapter = detailAdapter
        val space: Int = 12
        binding.recyclerDetail.addItemDecoration(SpaceItemDecoration(space))
    }

    override fun onItemClick(view: View?, position: Int) {
        var text = "Выбран элемент: "
        text += detailAdapter.getItem(position).firstName
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun doBack() {
        this.finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar, menu)
        return true
    }

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
        val myDialogFragment = MyDialogFragment()
        val manager = supportFragmentManager
        myDialogFragment.show(manager, "myDialog")
    }
}