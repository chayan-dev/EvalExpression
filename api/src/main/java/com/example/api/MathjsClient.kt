package com.example.api

import com.example.api.services.MathjsAPI
import com.example.api.utils.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object MathjsClient {

  private val retrofitBuilder = Retrofit.Builder()
    .baseUrl(BASE_URL)
//    .addConverterFactory(MoshiConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//  private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

  val api = retrofitBuilder.create(MathjsAPI::class.java)
}