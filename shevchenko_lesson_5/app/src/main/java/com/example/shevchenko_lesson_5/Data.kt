package com.example.shevchenko_lesson_5

import android.os.Parcel
import android.os.Parcelable

class Data() : Parcelable {
    private var value: String = ""

    constructor(parcel: Parcel) : this() {
    }
    fun isValueEmpty(): Boolean = (value == "")

    fun setValue(text: String?) {
        value += text
    }

    fun getAllValue(): String = value

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(value)
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }
        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}
