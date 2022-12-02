package com.plcoding.cryptocurrencyappyt.data.remote.model

import com.google.gson.annotations.SerializedName
import com.plcoding.cryptocurrencyappyt.domain.entity.CoinDetail

data class CoinDetailModel(
    val description: String,
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: String,
    val links: LinksModel,
    @SerializedName("links_extended")
    val linksExtended: List<LinkExtendedModel>,
    val logo: String,
    val message: String,
    val name: String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: String,
    @SerializedName("proof_type")
    val proofType: String,
    val rank: Int,
    @SerializedName("started_at")
    val startedAt: String,
    val symbol: String,
    val tags: List<TagDto>,
    val team: List<TeamMemberDto>,
    val type: String,
    val whitepaper: WhitepaperModel
)

fun CoinDetailModel.asCoinDetail() : CoinDetail {
    return CoinDetail(
        coinId = this.id,
        name = this.name,
        description = this.description,
        symbol = this.symbol,
        rank = this.rank,
        isActive = this.isActive,
        tags = this.tags.asTags(),
        team = this.team.asTeamMembers()
    )
}