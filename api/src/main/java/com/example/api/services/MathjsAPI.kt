package com.example.api.services

import com.example.api.models.ExpressionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MathjsAPI {

  @Headers("Content-Type: application/json")
  @POST("")
  suspend fun sendExpressions(
    @Body body: String
  ) : Response<ExpressionResponse>
}