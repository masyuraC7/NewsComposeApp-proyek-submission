package com.mc7.newscomposeapp.utils

sealed class ResultState<out T: Any?> {
    data class Success<out T: Any>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    object Empty : ResultState<Nothing>()
}