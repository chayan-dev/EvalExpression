package com.example.api.models


import com.google.gson.annotations.SerializedName

data class ExpResponse(
    @SerializedName("error")
    val error: Any?,
    @SerializedName("result")
    val result: List<String>
)