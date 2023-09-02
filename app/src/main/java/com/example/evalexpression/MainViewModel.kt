package com.example.evalexpression

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evalexpression.models.ExprBody
import com.example.evalexpression.repository.ExpressionRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

  private val  expressionRepository= ExpressionRepository()

  fun solveAction(exp: String){
    val expressionArray = exp.split("\n")
    Log.d("solveAction", expressionArray.toString())

    sendExpression(expressionArray)
  }

  private fun sendExpression(exprArray: List<String>) {
    viewModelScope.launch {

      val obj = ExprBody(exprArray)
      val gson = Gson()
      val json = gson.toJsonTree(obj).asJsonObject
      Log.d("solveAction", json.toString())

      val response  = expressionRepository.sendExpressions(json)
      Log.d("solveAction_response", response.body().toString())


    }
  }

}