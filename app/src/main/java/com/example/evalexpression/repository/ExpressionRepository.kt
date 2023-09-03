package com.example.evalexpression.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.api.MathjsClient
import com.example.api.models.ExpResponse
import com.example.evalexpression.db.ExpressionsDao
import com.example.evalexpression.db.entity.SavedExpr
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.exp

class ExpressionRepository @Inject constructor(private val exprDao: ExpressionsDao) {

  private val api = MathjsClient.api

  suspend fun sendExpressions(exp: JsonObject): Response<ExpResponse> {
    Log.d("solveAction", "before call")
    val response = api.sendExpressions(exp)
    Log.d("solveAction", "after call")
    return response
  }

  fun saveExpressions(data: SavedExpr){
    exprDao.insert(data)
  }

  fun getHistory() = exprDao.getSavedValues()

  fun getHistoryDates() = exprDao.getSavedDates()

  fun getExpsOnDate(date: String) = exprDao.getAllExpsOnDate(date)
}