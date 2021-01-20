package com.assignment.data.db

import androidx.room.TypeConverter
import com.assignment.data.About
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(list: List<About>?) = Gson().toJson(list)
}