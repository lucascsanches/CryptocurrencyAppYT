package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.data.remote.ICoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.repository.CoinRepository
import com.plcoding.cryptocurrencyappyt.domain.repository.ICoinRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    fun provideCoinRepository(api: ICoinPaprikaApi): ICoinRepository {
        return CoinRepository(api)
    }
}