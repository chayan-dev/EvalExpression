package com.example.evalexpression.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evalexpression.db.entity.SavedExpr
import com.example.evalexpression.repository.ExpressionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: ExpressionRepository): ViewModel() {

  lateinit var currentDate: String

  fun getSavedDates() = repository.getHistoryDates()

  fun getExpressionsOnDate() = repository.getExpsOnDate(currentDate)

  fun setDate(date: String) {
    currentDate = date
  }

}