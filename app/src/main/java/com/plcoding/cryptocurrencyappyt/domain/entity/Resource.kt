package com.plcoding.cryptocurrencyappyt.domain.entity

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class ServerError<T> : Resource<T>()
    class NoInternet<T> : Resource<T>()
    class Loading<T>() : Resource<T>()
}
