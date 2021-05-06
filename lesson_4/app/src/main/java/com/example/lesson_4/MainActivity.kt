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

    val detailInfoItems = ItemsRepository().getDetail()
    val baseInfoItems = ItemsRepository().getBase()
    private val AllInfoItems = detailInfoItems + baseInfoItems
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
                return if ((position >= detailInfoItems.count() - 1) && (detailInfoItems.count() % 2 != 0)) 2
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
        var text = getString(R.string.on_item_click)
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
        Toast.makeText(this, getString(R.string.home_toast), Toast.LENGTH_SHORT).show()
    }

    private fun doInfo() {
        val tag = getString(R.string.dialog_tag)
        val myDialogFragment = MyDialogFragment()
        val manager = supportFragmentManager
        myDialogFragment.show(manager, tag)
    }
}