package com.example.evalexpression.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.api.models.ExpResponse
import com.example.evalexpression.db.entity.SavedExpr
import com.example.evalexpression.getOrAwaitValue
import com.example.evalexpression.repository.ExpressionRepository
import com.example.evalexpression.ui.viewmodels.MainViewModel
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

  private val testDispatcher= StandardTestDispatcher()

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  @Mock
  lateinit var repository: ExpressionRepository

  @Before
  fun setUp() {
    MockitoAnnotations.openMocks(this)
    Dispatchers.setMain(testDispatcher)
  }

  @Test
  fun test_solveAction() = runTest{
    Mockito.`when`(repository.sendExpressions(JsonObject())).thenReturn(Response.success(ExpResponse("", listOf())))
    Mockito.`when`(repository.saveExpressions(SavedExpr(1,"", listOf())))

    val sut = MainViewModel(repository)
    sut.solveAction("abc")
    testDispatcher.scheduler.advanceUntilIdle()

    val result = sut.expressionsValues.getOrAwaitValue()
    assertEquals(0, result.size )

  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }
}