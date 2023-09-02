package com.example.api

import com.example.api.services.MathjsAPI
import com.example.api.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MathjsClient {

  private val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())

  val api = retrofitBuilder
    .build()
    .create(MathjsAPI::class.java)
}