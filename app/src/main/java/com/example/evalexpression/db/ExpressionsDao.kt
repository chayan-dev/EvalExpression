package com.example.evalexpression.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evalexpression.db.entity.SavedExpr

@Dao
interface ExpressionsDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insert(value: SavedExpr)

  @Query("SELECT DISTINCT date FROM solvedValues_table ORDER BY date")
  fun getSavedDates(): LiveData<List<String>>

  @Query("SELECT * FROM solvedValues_table WHERE DATE LIKE :givenDate ORDER BY date")
  fun getAllExpsOnDate(givenDate:String): LiveData<List<SavedExpr>>
}