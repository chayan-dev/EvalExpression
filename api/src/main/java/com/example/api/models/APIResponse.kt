package com.example.api.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Response

@JsonClass(generateAdapter = true)
data class APIResponse(
    @Json(name = "result")
    val result: List<String>,
    @Json(name = "error")
    val error: String?
)