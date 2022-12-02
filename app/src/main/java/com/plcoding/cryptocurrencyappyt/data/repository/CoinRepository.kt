package com.plcoding.cryptocurrencyappyt.data.repository

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.ICoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.model.asCoinDetail
import com.plcoding.cryptocurrencyappyt.data.remote.model.asCoins
import com.plcoding.cryptocurrencyappyt.domain.entity.Coin
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: ICoinPaprikaApi
): ICoinRepository {

    override suspend fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coinsResults = api.getCoins()
            emit(Resource.Success(coinsResults.asCoins()))
        } catch (e: HttpException) {
            emit(Resource.ServerError())
        } catch (e: IOException) {
            emit(Resource.NoInternet())
        }
    }

    override suspend fun getCoinDetails(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coinDetailResult = api.getCoinDetail(coinId)
            emit(Resource.Success(coinDetailResult.asCoinDetail()))
        } catch (e: HttpException) {
            emit(Resource.ServerError())
        } catch (e: IOException) {
            emit(Resource.NoInternet())
        }
    }

}