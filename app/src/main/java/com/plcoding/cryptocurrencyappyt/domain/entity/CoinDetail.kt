package com.plcoding.cryptocurrencyappyt.domain.entity

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<Tag>,
    val team: List<TeamMember>
)