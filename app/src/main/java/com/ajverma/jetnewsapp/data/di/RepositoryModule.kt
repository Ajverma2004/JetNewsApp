package com.ajverma.jetnewsapp.data.di

import com.ajverma.jetnewsapp.data.repositoryImpl.NewsRepositoryImpl
import com.ajverma.jetnewsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(newsRepositoryImpl: NewsRepositoryImpl):NewsRepository
}