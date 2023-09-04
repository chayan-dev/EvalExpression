package com.example.evalexpression.di

import com.example.api.MathjsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

  @Provides
  fun provideApi() = MathjsClient.api

}