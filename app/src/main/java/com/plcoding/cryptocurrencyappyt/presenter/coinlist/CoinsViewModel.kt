package com.plcoding.cryptocurrencyappyt.presenter.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.domain.entity.Coin
import com.plcoding.cryptocurrencyappyt.domain.entity.Resource
import com.plcoding.cryptocurrencyappyt.domain.usecase.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presenter.state.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    private val _coinsState = mutableStateOf(CoinsState())
    val coinsState: State<CoinsState> = _coinsState

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            _coinsState.value = when (result) {
                is Resource.Loading -> getLoadingCoinsState()
                is Resource.NoInternet -> getNoInternetCoinsState()
                is Resource.ServerError -> getServerErrorCoinsState()
                is Resource.Success -> getSuccessCoinsState(result.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getLoadingCoinsState(): CoinsState {
        return CoinsState(pageState = PageState.LOADING)
    }

    private fun getServerErrorCoinsState(): CoinsState {
        return CoinsState(pageState = PageState.SERVER_ERROR)
    }

    private fun getNoInternetCoinsState(): CoinsState {
        return CoinsState(pageState = PageState.NO_INTERNET_ERROR)
    }

    private fun getSuccessCoinsState(coins: List<Coin>): CoinsState {
        return CoinsState(
            coins = coins,
            pageState = PageState.SUCCESS
        )
    }
}