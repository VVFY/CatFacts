package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.dao.CatFactsDao
import com.example.data.local.db.CatFactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext context: Context): CatFactDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CatFactDatabase::class.java,
            CatFactDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCatFactDAO(appDatabase: CatFactDatabase): CatFactsDao {
        return appDatabase.catFactDao()
    }
}