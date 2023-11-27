package com.mc7.newscomposeapp.data.repository

import com.mc7.newscomposeapp.data.model.NewsEntity
import com.mc7.newscomposeapp.data.model.dummyNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {
    override fun searchNews(query: String) = flow {
        val data = dummyNews.filter {
            it.title.contains(query, ignoreCase = true)
        }

        emit(data)
    }

    override fun getFavoritedNews() = flow {
        val data = dummyNews.filter {
            it.isFavorited.and(true)
        }

        emit(data)
    }

    override fun getNewsDetail(title: String): Flow<NewsEntity> {
        val data = dummyNews.first{
            it.title == title
        }

        return flowOf(data)
    }

    override fun updateNews(title: String, newState: Boolean): Flow<Boolean> {
        val index = dummyNews.indexOfFirst { it.title == title }
        val result = if (index >= 0) {
            val news = dummyNews[index]
            dummyNews[index] = news.copy(isFavorited = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }
}
