package com.example.api

import com.example.api.models.ExprBody
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MathjsClientTests {

  private val mathjsClient = MathjsClient

  @Test
  fun test_sendExpression() = runBlocking{

    val exprList = listOf("1+2","2+5")
    val obj = ExprBody(exprList)
    val gson = Gson()
    val json = gson.toJsonTree(obj).asJsonObject

    val result = mathjsClient.api.sendExpressions(json)
    Assert.assertNotNull(result.body()?.result)
  }

}