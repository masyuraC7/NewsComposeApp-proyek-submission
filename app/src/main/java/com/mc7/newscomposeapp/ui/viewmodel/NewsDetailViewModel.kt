package com.mc7.newscomposeapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mc7.newscomposeapp.data.model.NewsEntity
import com.mc7.newscomposeapp.data.repository.NewsRepository
import com.mc7.newscomposeapp.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {
    private val _resultState: MutableStateFlow<ResultState<NewsEntity>> =
        MutableStateFlow(ResultState.Loading)
    val resultState: StateFlow<ResultState<NewsEntity>>
        get() = _resultState

    fun getNewsDetail(title: String) = viewModelScope.launch {
        repository.getNewsDetail(title)
            .catch {
                _resultState.value = ResultState.Error(it.message.toString())
            }
            .collect {
                _resultState.value = ResultState.Success(it)
            }
    }

    fun updateNews(title: String, newState: Boolean) = viewModelScope.launch {
        repository.updateNews(title, newState)
            .collect { isUpdated ->
                if (isUpdated) getNewsDetail(title)
            }
    }
}