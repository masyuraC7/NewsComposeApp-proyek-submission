package com.mc7.newscomposeapp.data.di

import android.content.Context
import com.mc7.newscomposeapp.data.repository.NewsRepository
import com.mc7.newscomposeapp.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideMyApp(@ApplicationContext context: Context): MyApp{
        return context as MyApp
    }

    @Provides
    fun provideNewsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}