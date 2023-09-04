package com.example.evalexpression.repository

import com.example.api.models.ExpResponse
import com.example.api.services.MathjsAPI
import com.example.evalexpression.db.ExpressionsDao
import com.example.evalexpression.models.ExprBody
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.moshi.Json
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ExpressionRepositoryTest {

  @Mock
  lateinit var expressionsDao: ExpressionsDao

  @Mock
  lateinit var api: MathjsAPI

  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun testSendExpressions()= runTest{
    Mockito.`when`(api.sendExpressions(JsonObject())).
      thenReturn(Response.success(ExpResponse("", emptyList())))

    val sut = ExpressionRepository(expressionsDao,api)
    val result = sut.sendExpressions(JsonObject())

    assertEquals(emptyList<String>(),result.body()?.result)
    assertEquals(0, result.body()!!.result.size )
  }

  @Test
  fun testSendExpressions_validInput()= runTest{

    val exprList = listOf("1+2","2+5")
    val obj = ExprBody(exprList)
    val gson = Gson()
    val json = gson.toJsonTree(obj).asJsonObject

    Mockito.`when`(api.sendExpressions(json)).
    thenReturn(Response.success(ExpResponse("", listOf("3","7"))))

    val sut = ExpressionRepository(expressionsDao,api)
    val result = sut.sendExpressions(json)

    assertEquals("3",result.body()!!.result[0])
  }

  @After
  fun tearDown() {
  }
}