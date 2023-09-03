package com.example.evalexpression.di

import android.content.Context
import androidx.room.Room
import com.example.evalexpression.db.ExpressionsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Singleton
  @Provides
  fun providesExpressionsDatabase(
    @ApplicationContext context: Context
  ) : ExpressionsDatabase {

    return Room.databaseBuilder(
      context,
      ExpressionsDatabase::class.java,
      ExpressionsDatabase.DB_NAME
    ).build()

  }

  @Singleton
  @Provides
  fun providesExpressionsDao(
    expressionsDatabase: ExpressionsDatabase
  ) = expressionsDatabase.getExpressionsDao()
}