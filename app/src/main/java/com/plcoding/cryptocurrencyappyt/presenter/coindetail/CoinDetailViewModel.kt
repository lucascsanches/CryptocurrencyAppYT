package com.plcoding.cryptocurrencyappyt.presenter.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.entity.Resource
import com.plcoding.cryptocurrencyappyt.domain.usecase.GetCoinDetailUseCase
import com.plcoding.cryptocurrencyappyt.presenter.state.PageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
): ViewModel() {

    private val _coinDetailState = mutableStateOf(CoinDetailState())
    val coinDetailState: State<CoinDetailState> = _coinDetailState

    suspend fun getCoinDetail(coinId: String) {
        getCoinDetailUseCase(coinId).collect { result ->
            _coinDetailState.value = when (result) {
                is Resource.Loading -> getLoadingCoinDetailState()
                is Resource.NoInternet -> getNoInternetCoinDetailState()
                is Resource.ServerError -> getServerErrorCoinDetailState()
                is Resource.Success -> getSuccessCoinDetailState(result.data)
            }
        }
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
}