package com.plcoding.cryptocurrencyappyt.presenter.coinlist

import com.plcoding.cryptocurrencyappyt.domain.entity.Coin
import com.plcoding.cryptocurrencyappyt.presenter.state.PageState

class CoinsState(
    val coins: List<Coin>? = null,
    val pageState: PageState = PageState.IDLE
)