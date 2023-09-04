package com.example.evalexpression.di

import com.example.api.services.MathjsAPI
import com.example.evalexpression.db.ExpressionsDao
import com.example.evalexpression.repository.ExpressionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

  @Provides
  fun providesExpressionsRepository(exprDao: ExpressionsDao, api: MathjsAPI) =
    ExpressionRepository(exprDao,api)
}