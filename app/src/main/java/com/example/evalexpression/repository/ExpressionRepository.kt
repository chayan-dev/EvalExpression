package com.example.evalexpression.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.api.MathjsClient
import com.example.api.models.ExpResponse
import com.example.api.services.MathjsAPI
import com.example.evalexpression.db.ExpressionsDao
import com.example.evalexpression.db.entity.SavedExpr
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.exp

class ExpressionRepository @Inject constructor(
  private val exprDao: ExpressionsDao,
  private val api: MathjsAPI
  ) {

  suspend fun sendExpressions(exp: JsonObject): Response<ExpResponse> = api.sendExpressions(exp)

  fun saveExpressions(data: SavedExpr)= exprDao.insert(data)

  fun getHistoryDates() = exprDao.getSavedDates()

  fun getExpsOnDate(date: String) = exprDao.getAllExpsOnDate(date)
}