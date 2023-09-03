package com.example.evalexpression.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evalexpression.db.entity.SavedExpr
import com.example.evalexpression.models.ExprBody
import com.example.evalexpression.repository.ExpressionRepository
import com.example.evalexpression.utils.getCurrentDateTime
import com.example.evalexpression.utils.toString
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ExpressionRepository): ViewModel() {

  private var _expressionsValues: MutableLiveData<List<Pair<String,String>>> = MutableLiveData(listOf())
  val expressionsValues: MutableLiveData<List<Pair<String,String>>> = _expressionsValues

  fun solveAction(exp: String){
    var expressionArray = exp.split("\n")
    if(expressionArray.last().isBlank()){
      expressionArray= expressionArray.dropLast(1)
    }
    Log.d("solveAction", expressionArray.toString())

    sendExpression(expressionArray)
  }

  private fun sendExpression(exprList: List<String>) {
//    _expressionsValues = MutableLiveData()
    viewModelScope.launch {

      val obj = ExprBody(exprList)
      val gson = Gson()
      val json = gson.toJsonTree(obj).asJsonObject
      Log.d("solveAction", json.toString())

      val response  = repository.sendExpressions(json)
      Log.d("solveAction_response", response.body().toString())
      response.body()?.let { setEvaluationResult(it.result, exprList) }

    }
  }

  private fun setEvaluationResult(result: List<String>, exp: List<String>){

    val results = mutableListOf<Pair<String,String>>()
    for (i in exp.indices){
      results.add(Pair(exp[i],result[i]))
    }
    Log.d("expValue", results.toString())

    _expressionsValues.postValue(results)
    viewModelScope.launch(Dispatchers.IO){
      repository.saveExpressions(SavedExpr(date = getCurrentTime(), values =  results))
    }

  }

   fun setEvaluationResultFromDB(expression:SavedExpr, pos: Int){
     _expressionsValues.value = expression.values
  }

  private fun getCurrentTime(): String {
    val date = getCurrentDateTime()
    return date.toString("dd/MM/yyyy")
  }

  fun resetBottomSheetData() {
    _expressionsValues.value = listOf()
  }

}