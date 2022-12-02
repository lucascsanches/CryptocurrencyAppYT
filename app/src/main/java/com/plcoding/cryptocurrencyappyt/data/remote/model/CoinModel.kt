package com.plcoding.cryptocurrencyappyt.data.remote.model

import com.google.gson.annotations.SerializedName
import com.plcoding.cryptocurrencyappyt.domain.entity.Coin

data class CoinModel(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun List<CoinModel>.asCoins(): List<Coin> {
    return this.map { it.asCoin() }
}

fun CoinModel.asCoin(): Coin {
    return Coin(
        id = this.id,
        isActive = this.isActive,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol
    )
}