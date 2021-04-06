package com.example.shevchenko_lesson_5

import android.os.Parcel
import android.os.Parcelable

class Data: Parcelable {
    private var value: MutableList<String?> = mutableListOf()

    fun isValueEmpty(): Boolean = (value.size == 0)

    fun setValue(text: String?) {
        if (value.size == 0) value = mutableListOf(text)
        else value.add(text)
    }

    fun getAllValue(): MutableList<String?> = this.value
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
}
