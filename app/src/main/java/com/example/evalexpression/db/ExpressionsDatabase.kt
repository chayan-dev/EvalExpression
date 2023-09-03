package com.example.evalexpression.db

import android.content.Context
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

//    @Volatile
//    private var INSTANCE: ExpressionsDatabase? = null
//
//    fun getDatabase(context: Context): ExpressionsDatabase{
//      val tempInstance = INSTANCE
//      if(tempInstance!=null){
//        return tempInstance
//      }
//      synchronized(this){
//        val instance = Room.databaseBuilder(
//          context.applicationContext,
//          ExpressionsDatabase::class.java,
//          "expressions_database"
//        ).build()
//        INSTANCE = instance
//        return instance
//      }
//    }
  }
}