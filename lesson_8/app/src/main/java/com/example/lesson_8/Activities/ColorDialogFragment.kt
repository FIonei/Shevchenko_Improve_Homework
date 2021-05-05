package com.example.lesson_8.Activities

import android.app.Dialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.lesson_8.R
import com.example.lesson_8.databinding.ColorDialogBinding


class ColorDialogFragment() : DialogFragment() {
    private lateinit var binding: ColorDialogBinding
    private var currentColor: String = "white"
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_COLOR = "CurrentColor"
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val prefs: SharedPreferences? = context?.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        currentColor = prefs?.getString(APP_PREFERENCES_COLOR, "white") ?: "white"

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = ColorDialogBinding.inflate(layoutInflater)
            builder.setView(binding.root)
            binding.cancelButton.setOnClickListener { cancel() }
            binding.accessButton.setOnClickListener { confirm() }
            setColorClickListeners()
            binding.root.findViewWithTag<ImageView>(currentColor)
                .setImageResource(R.drawable.ic_check)
            builder.create()
        } ?: throw IllegalStateException(getString(R.string.exception_not_null_activity))
    }

    private fun setColorClickListeners() {
        binding.oval00.setOnClickListener { chooseColor(binding.oval00) }
        binding.oval01.setOnClickListener { chooseColor(binding.oval01) }
        binding.oval02.setOnClickListener { chooseColor(binding.oval02) }
        binding.oval03.setOnClickListener { chooseColor(binding.oval03) }
        binding.oval10.setOnClickListener { chooseColor(binding.oval10) }
        binding.oval11.setOnClickListener { chooseColor(binding.oval11) }
        binding.oval12.setOnClickListener { chooseColor(binding.oval12) }
        binding.oval13.setOnClickListener { chooseColor(binding.oval13) }
        binding.oval20.setOnClickListener { chooseColor(binding.oval20) }
        binding.oval21.setOnClickListener { chooseColor(binding.oval21) }
        binding.oval22.setOnClickListener { chooseColor(binding.oval22) }
        binding.oval23.setOnClickListener { chooseColor(binding.oval23) }
        binding.oval30.setOnClickListener { chooseColor(binding.oval30) }
        binding.oval31.setOnClickListener { chooseColor(binding.oval31) }
        binding.oval32.setOnClickListener { chooseColor(binding.oval32) }
        binding.oval33.setOnClickListener { chooseColor(binding.oval33) }
    }

    private fun confirm() {
        val editor = context?.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)?.edit()
        editor?.putString(APP_PREFERENCES_COLOR, currentColor)
        editor?.apply()
        dismiss()
    }

    private fun cancel() {
        dismiss()
    }

    private fun chooseColor(view: ImageView) {
        binding.root.findViewWithTag<ImageView>(currentColor)
            .setImageResource(android.R.color.transparent)
        view.setImageResource(R.drawable.ic_check)
        currentColor = view.tag.toString()
        binding.accessButton.visibility = VISIBLE
    }
}