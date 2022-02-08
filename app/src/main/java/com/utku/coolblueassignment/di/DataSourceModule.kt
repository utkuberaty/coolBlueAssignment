package com.utku.coolblueassignment.di

import com.utku.coolblueassignment.data.remote.CoolBlueService
import com.utku.coolblueassignment.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt singleton Component module for data sources
 * */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun remoteDataSource(coolBlueService: CoolBlueService) = RemoteDataSource(coolBlueService)
}