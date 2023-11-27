package com.mc7.newscomposeapp.data.repository

import com.mc7.newscomposeapp.data.model.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun searchNews(query: String): Flow<List<NewsEntity>>
    fun getFavoritedNews(): Flow<List<NewsEntity>>
    fun getNewsDetail(title: String): Flow<NewsEntity>
    fun updateNews(title: String, newState: Boolean): Flow<Boolean>
}