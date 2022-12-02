package com.plcoding.cryptocurrencyappyt.data.remote.model

import com.google.gson.annotations.SerializedName
import com.plcoding.cryptocurrencyappyt.domain.entity.Tag

data class TagDto(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    val id: String,
    val name: String
)

fun List<TagDto>.asTags(): List<Tag> {
    return this.map {
        it.asTag()
    }
}

fun TagDto.asTag(): Tag {
    return Tag(
        name = this.name
    )
}