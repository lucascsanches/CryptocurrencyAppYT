package com.plcoding.cryptocurrencyappyt.common

sealed class Resource<T> {
    class Success<T>(data: T) : Resource<T>()
    class ServerError<T> : Resource<T>()
    class NoInternet<T> : Resource<T>()
    class Loading<T>() : Resource<T>()
}
