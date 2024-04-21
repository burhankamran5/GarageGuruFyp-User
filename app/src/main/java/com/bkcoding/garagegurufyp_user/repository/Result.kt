package com.bkcoding.garagegurufyp_user.repository

sealed class Result<out T> {
    data class Success<out R>(val data:R) : Result<R>()
    data class Failure(val msg:Throwable) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}