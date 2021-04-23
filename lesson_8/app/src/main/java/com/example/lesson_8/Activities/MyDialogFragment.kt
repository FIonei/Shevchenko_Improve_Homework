package com.example.lesson_8.Activities

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.room.Room.databaseBuilder
import com.example.lesson_8.R
import com.example.lesson_8.Room.NoteDatabase

class MyDialogFragment (val con: Context, private val position: Long) : DialogFragment() {
    val db = databaseBuilder(
    con,
    NoteDatabase::class.java, "Notes"
    )
    .allowMainThreadQueries()//TODO: заменить на корутины
    .build()
    val noteDao = db.noteDao()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.alert_title))
                .setMessage(getString(R.string.alert_message))
                .setPositiveButton(getString(R.string.alert_positive)) { dialog, id ->
                    noteDao.archive(position)
                    dialog.cancel()
                }
                .setNegativeButton(getString(R.string.alert_negative)) { dialog, id ->
                    noteDao.delete(position)
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.exception_not_null_activity))
    }
}
