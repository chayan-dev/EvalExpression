package com.example.evalexpression.repository

import android.util.Log
import com.example.api.MathjsClient
import com.example.api.models.ExpResponse
import com.example.api.models.ExpressionResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response

class ExpressionRepository {

  private val api = MathjsClient.api

  suspend fun sendExpressions(exp: JsonObject): Response<ExpResponse> {
    Log.d("solveAction", "before call")
    val response = api.sendExpressions(exp)
    Log.d("solveAction", "after call")
    return response
  }
}