package com.example.api.services

import com.example.api.models.ExpResponse
import com.example.api.models.ExpressionResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MathjsAPI {

  @Headers("Content-Type: application/json")
  @POST("v4/")
  suspend fun sendExpressions(
    @Body body: JsonObject
  ) : Response<ExpResponse>
}