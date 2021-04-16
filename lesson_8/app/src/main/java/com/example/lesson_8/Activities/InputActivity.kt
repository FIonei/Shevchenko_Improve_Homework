package com.example.lesson_8.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_8.MapOfColors
import com.example.lesson_8.Room.NoteEntity
import com.example.lesson_8.R
import com.example.lesson_8.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    private var colorTag: String = MapOfColors().getCurrentColor()
    private val EXTRA_NAME = "note"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInputBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = null
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener { save() }
    }

    private fun save() {
        if ((binding.text.text.isEmpty() && (binding.title.text.isEmpty()))) {
            setResult(RESULT_CANCELED)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(
                EXTRA_NAME,
                NoteEntity(
                    title = binding.title.text?.toString() ?: "",
                    text = binding.text.text?.toString() ?: "",
                    color = MapOfColors().getCurrentColor(),
                )
            )
            setResult(RESULT_OK, intent)
        }
        finish()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_input, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_color -> {
                openColorChanging()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openColorChanging() {
        val colorDialog = ColorDialogFragment(colorTag)
        val manager = supportFragmentManager
        colorDialog.show(manager, getString(R.string.color_dialog_tag))
    }
}