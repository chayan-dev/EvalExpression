package com.example.evalexpression.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
  tableName = "solvedValues_table"
)
data class SavedExpr(
  @PrimaryKey(autoGenerate = true)
  var id: Int? = null,
  val date: String,
  val values: List<Pair<String,String>>
)
