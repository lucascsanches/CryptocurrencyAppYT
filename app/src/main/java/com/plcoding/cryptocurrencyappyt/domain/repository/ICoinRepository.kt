package com.plcoding.cryptocurrencyappyt.domain.repository

import com.plcoding.cryptocurrencyappyt.domain.entity.Resource
import com.plcoding.cryptocurrencyappyt.domain.entity.Coin
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import kotlinx.coroutines.flow.Flow

interface ICoinRepository {

    suspend fun getCoins(): Flow<Resource<List<Coin>>>

    suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>>
}