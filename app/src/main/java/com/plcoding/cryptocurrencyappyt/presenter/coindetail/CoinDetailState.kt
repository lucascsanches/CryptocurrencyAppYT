package com.plcoding.cryptocurrencyappyt.presenter.coindetail

import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail
import com.plcoding.cryptocurrencyappyt.presenter.state.PageState

class CoinDetailState(
    val coinDetail: CoinDetail? = null,
    val pageState: PageState = PageState.IDLE
)