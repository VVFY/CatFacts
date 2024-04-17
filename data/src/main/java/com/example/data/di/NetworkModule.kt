package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.remote.service.CatFactService
import com.example.data.remote.utils.MoshiInstance
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(MoshiInstance.default)
            )
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideCatFactService(retrofit: Retrofit): CatFactService {
        return retrofit.create(CatFactService::class.java)
    }
}