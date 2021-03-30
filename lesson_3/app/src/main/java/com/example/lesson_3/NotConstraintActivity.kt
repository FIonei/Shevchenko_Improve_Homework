package com.example.lesson_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.lesson_3.databinding.ActivityConstraintBinding
import com.example.lesson_3.databinding.ActivityNotConstraintBinding

class NotConstraintActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotConstraintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        initTitle()
        initRow1()
        initRow2()
        initRow3()
        initRow4()
        initRow5()
        initMain()
    }

    private fun initRow1() {
        binding.row1.nameText.text = getString(R.string.row1_name)
        binding.row1.text.text = getString(R.string.row1_text)
        binding.row1.root.setOnClickListener { showText(binding.row1.nameText.text.toString()) }
    }

    private fun initRow2() {
        binding.row2.nameText.text = getString(R.string.row2_name)
        binding.row2.text.text = getString(R.string.row2_text)
        binding.row2.root.setOnClickListener { showText(binding.row2.nameText.text.toString()) }
    }

    private fun initRow3() {
        binding.row3.nameText.text = getString(R.string.row3_name)
        binding.row3.text.text = getString(R.string.row3_text)
        binding.row3.root.setOnClickListener { showText(binding.row3.nameText.text.toString()) }
    }

    private fun initRow4() {
        binding.row4.nameText.text = getString(R.string.row4_name)
        binding.row4.text.text = getString(R.string.row4_text)
        binding.row4.root.setOnClickListener { showText(binding.row4.nameText.text.toString()) }
    }

    private fun initRow5() {
        binding.row5.nameText.text = getString(R.string.row5_name)
        binding.row5.text.text = getString(R.string.row5_text)
        binding.row5.root.setOnClickListener { showText(binding.row5.nameText.text.toString()) }
        binding.row5.imageButton.setOnClickListener { showText(getString(R.string.toast_edit)) }
    }

    private fun initTitle() {
        binding.titleBar.titleText.text = getString(R.string.toolbar_title)
        binding.titleBar.textWorker.text = getString(R.string.worker_card_text).trimMargin()
        binding.titleBar.titleBack.setOnClickListener { showText(getString(R.string.toast_back)) }
        binding.titleBar.titleEdit.setOnClickListener { showText(getString(R.string.toast_edit_profile)) }
    }

    private fun initMain() {
        binding.textHead.text = getString(R.string.head_text)
        binding.exitText.text = getString(R.string.exit_text)
        binding.imageExit.setOnClickListener { showText(getString(R.string.toast_exit)) }
        binding.exitText.setOnClickListener { showText(getString(R.string.toast_exit)) }
    }

    private fun showText(s: String) =
            Toast.makeText(binding.root.context, s, Toast.LENGTH_SHORT).show()
}