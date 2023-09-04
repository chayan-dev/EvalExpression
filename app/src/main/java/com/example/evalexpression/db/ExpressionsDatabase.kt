package com.example.evalexpression.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.evalexpression.db.entity.SavedExpr

@Database(
  entities = [SavedExpr::class],
  version = 1,
  exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExpressionsDatabase: RoomDatabase() {

  abstract fun getExpressionsDao() : ExpressionsDao

  companion object{
    const val DB_NAME = "expressions_database"
  }
}