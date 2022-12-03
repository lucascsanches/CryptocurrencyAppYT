package com.plcoding.cryptocurrencyappyt.domain.usecase

import com.plcoding.cryptocurrencyappyt.domain.entity.Resource
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.ICoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val coinRepository: ICoinRepository
) {

    suspend operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> {
        return coinRepository.getCoinDetails(coinId)
    }
}