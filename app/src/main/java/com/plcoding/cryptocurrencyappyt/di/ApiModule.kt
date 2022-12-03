package com.plcoding.cryptocurrencyappyt.di

import com.plcoding.cryptocurrencyappyt.data.remote.ICoinPaprikaApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {

    fun provideCoinPaprikaApi(): ICoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(ICoinPaprikaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ICoinPaprikaApi::class.java)
    }
}