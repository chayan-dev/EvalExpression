package com.example.evalexpression.ui.viewmodels

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

  //called on clicking solve button
  fun solveAction(exp: String){
    var expressionArray = exp.split("\n")
    if(expressionArray.last().isBlank()){
      expressionArray= expressionArray.dropLast(1)
    }

    sendExpression(expressionArray)
  }

  //send the user input expression to api
  private fun sendExpression(exprList: List<String>) {
    viewModelScope.launch {

      val obj = ExprBody(exprList)
      val gson = Gson()
      val json = gson.toJsonTree(obj).asJsonObject

      val response  = repository.sendExpressions(json)

      //handle response from api
      response.body()?.let { setEvaluationResult(it.result, exprList) }
    }
  }

  private fun setEvaluationResult(result: List<String>, exp: List<String>){

    val results = mutableListOf<Pair<String,String>>()
    for (i in exp.indices){
      results.add(Pair(exp[i],result[i]))
    }

    _expressionsValues.postValue(results)

    //saving in db
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