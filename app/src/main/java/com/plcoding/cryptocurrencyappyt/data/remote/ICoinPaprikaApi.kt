package com.plcoding.cryptocurrencyappyt.data.remote

import com.plcoding.cryptocurrencyappyt.data.remote.model.CoinDetailModel
import com.plcoding.cryptocurrencyappyt.data.remote.model.CoinModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ICoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinModel>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinDetail(@Path(value = "coinId") coinId: String): CoinDetailModel
}