package com.plcoding.cryptocurrencyappyt.presenter.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.entity.Resource
import com.plcoding.cryptocurrencyappyt.domain.usecase.GetCoinDetailUseCase
import com.plcoding.cryptocurrencyappyt.presenter.state.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _coinDetailState = mutableStateOf(CoinDetailState())
    val coinDetailState: State<CoinDetailState> = _coinDetailState

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    private fun getCoinDetail(coinId: String) {
        getCoinDetailUseCase(coinId).onEach { result ->
            _coinDetailState.value = when (result) {
                is Resource.Loading -> getLoadingCoinDetailState()
                is Resource.NoInternet -> getNoInternetCoinDetailState()
                is Resource.ServerError -> getServerErrorCoinDetailState()
                is Resource.Success -> getSuccessCoinDetailState(result.data)
            }
        }.launchIn(viewModelScope)
    }

    private fun getLoadingCoinDetailState(): CoinDetailState {
        return CoinDetailState(pageState = PageState.LOADING)
    }

    private fun getServerErrorCoinDetailState(): CoinDetailState {
        return CoinDetailState(pageState = PageState.SERVER_ERROR)
    }

    private fun getNoInternetCoinDetailState(): CoinDetailState {
        return CoinDetailState(pageState = PageState.NO_INTERNET_ERROR)
    }

    private fun getSuccessCoinDetailState(coinDetail: CoinDetail): CoinDetailState {
        return CoinDetailState(
            coinDetail = coinDetail,
            pageState = PageState.SUCCESS
        )
    }

    companion object {
        const val PARAM_COIN_ID = "coinId"
    }
}