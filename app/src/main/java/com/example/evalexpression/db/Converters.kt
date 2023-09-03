package com.example.evalexpression.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
  @TypeConverter
  fun fromPairList(value: List<Pair<String, String>>): String {
    return Gson().toJson(value)
  }

  @TypeConverter
  fun toPairList(value: String): List<Pair<String, String>> {
    val listType = object : TypeToken<List<Pair<String, String>>>() {}.type
    return Gson().fromJson(value, listType)
  }
}