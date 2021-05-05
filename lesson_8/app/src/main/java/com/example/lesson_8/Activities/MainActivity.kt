package com.example.lesson_8.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.room.Room.databaseBuilder
import com.example.lesson_8.*
import com.example.lesson_8.Room.NoteDao
import com.example.lesson_8.Room.NoteDatabase
import com.example.lesson_8.Room.NoteEntity
import com.example.lesson_8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val EXTRA_NAME = "note"
    private val REQUEST_CODE: Int = 0
    var list: List<NoteEntity?>? = listOf()
    lateinit var db: NoteDatabase
    lateinit var noteDao: NoteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        db = databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "Notes"
        )
            .allowMainThreadQueries()//TODO: заменить на корутины
            .build()
        noteDao = db.noteDao()
        //пример добавления записи в БД
        //noteDao.insert(NoteEntity(title = "заметка №1", text = "что-то там написано про 1", color = getString(R.string.marigold), isArched = 0))
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        list = noteDao.getAllVisible()

        val recycler = binding.recycler
        recycler.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
        recycler.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.decoration_space)))

        recycler.adapter = createAdapter(list)
        binding.fab.setOnClickListener { _ -> createNewNote() }
        binding.fab.setOnLongClickListener { _ -> update() }
    }

    private fun update(): Boolean{
        list = noteDao.getAllVisible()
        binding.recycler.adapter = createAdapter(list)
        return true
    }
    private fun createAdapter(list: List<NoteEntity?>?): MyRecyclerAdapter{
        val adapter = MyRecyclerAdapter(this, list)
        adapter.setLongClickListener(object : MyRecyclerAdapter.ItemLongClickListener {
            override fun onItemLongClick(view: View?, position: Int) {
                openDialog(position)
            }
        })
        return adapter
    }
    private fun openDialog(pos: Int) {
        val myDialogFragment = MyDialogFragment(this, list!![pos]!!.id)
        val manager = supportFragmentManager
        myDialogFragment.show(manager, getString(R.string.dialog_tag))
    }

    private fun createNewNote() {
        val intent = Intent(this@MainActivity, InputActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE) && (resultCode == RESULT_OK)) {
            val note = data!!.getParcelableExtra<NoteEntity>(EXTRA_NAME)
            noteDao.insert(
                NoteEntity(
                    title = note?.title ?: "",
                    text = note?.text ?: "",
                    color = note?.color ?: getString(R.string.white),
                    isArched = note?.isArched ?: 0
                )
            )
        }
        list = noteDao.getAllVisible()
        binding.recycler.adapter = createAdapter(list)
    }
}