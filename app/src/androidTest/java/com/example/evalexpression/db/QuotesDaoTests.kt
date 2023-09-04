package com.example.evalexpression.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.evalexpression.db.entity.SavedExpr
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuotesDaoTests {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  lateinit var expressionsDatabase: ExpressionsDatabase
  lateinit var expressionsDao: ExpressionsDao

  @Before
  fun setup(){
    expressionsDatabase = Room.inMemoryDatabaseBuilder(
      ApplicationProvider.getApplicationContext(),
      ExpressionsDatabase::class.java
    ).allowMainThreadQueries().build()

    expressionsDao = expressionsDatabase.getExpressionsDao()
  }

  @Test
  fun insertExpression() = runBlocking {
    val expression = SavedExpr(0, "04/09/2023", listOf(Pair("5*2","10")) )
    expressionsDao.insert(expression)

    val result = expressionsDao.getSavedDates().getOrAwaitValue()

    Assert.assertEquals(1, result.size)
    Assert.assertEquals("04/09/2023", result[0])
  }

  @Test
  fun insertExprsAndGetOnDate() = runBlocking {
    val expression = SavedExpr(0, "04/09/2023", listOf(Pair("5*2","10")) )
    expressionsDao.insert(expression)
    val expression1 = SavedExpr(0, "03/09/2023", listOf(Pair("7*9","63")) )
    expressionsDao.insert(expression1)

    val result = expressionsDao.getAllExpsOnDate("04/09/2023").getOrAwaitValue()

    Assert.assertEquals(1, result.size)
    Assert.assertEquals("04/09/2023", result[0].date)
  }

  @After
  fun tearDown(){
    expressionsDatabase.close()
  }
}