package com.utku.coolblueassignment.di

import com.utku.coolblueassignment.data.remote.RemoteDataSource
import com.utku.coolblueassignment.data.repository.CoolBlueRepository
import com.utku.coolblueassignment.data.repository.CoolBlueRepositoryIml
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Hilt singleton Component module for repositories
 * */

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun coolBlueRepository(
        remoteDataSource: RemoteDataSource
    ): CoolBlueRepository = CoolBlueRepositoryIml(remoteDataSource)
}