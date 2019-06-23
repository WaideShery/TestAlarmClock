package com.neirx.testalarmclock.data.room.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * Created by Waide Shery on 19.06.19.
 */
class IntArrayConverter {
    private val gson = Gson()

    @TypeConverter
    fun intArrayToJson(array: IntArray?): String? {
        return if (array == null) null else gson.toJson(array)
    }

    @TypeConverter
    fun jsonToIntArray(json: String?): IntArray {
        return if (json == null) IntArray(0) else gson.fromJson(json, IntArray::class.java)
    }
}
