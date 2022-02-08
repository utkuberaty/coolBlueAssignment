package com.utku.coolblueassignment.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.utku.coolblueassignment.data.remote.CoolBlueService
import com.utku.coolblueassignment.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * This is kotlin serialization, use for convert json to data class
     * or data class to json
     * */

    @Provides
    @Singleton
    fun json(): Json = Json {
        prettyPrint = true
        isLenient = true
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    /**
     * Http client to connect API
     * All TimeOuts 20 seconds
     * Uses [HttpLoggingInterceptor] to log responses or requests
     * */

    @Provides
    @Singleton
    fun okhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun coolBlueService(retrofit: Retrofit): CoolBlueService =
        retrofit.create(CoolBlueService::class.java)

}