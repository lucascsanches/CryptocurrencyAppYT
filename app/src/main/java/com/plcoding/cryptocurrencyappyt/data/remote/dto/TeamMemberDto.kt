package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.plcoding.cryptocurrencyappyt.domain.model.TeamMember

data class TeamMemberDto(
    val id: String,
    val name: String,
    val position: String
)

fun List<TeamMemberDto>.asTeamMembers(): List<TeamMember> {
    return this.map { it.asTeamMember() }
}

fun TeamMemberDto.asTeamMember(): TeamMember {
    return TeamMember(
        id = this.id,
        name = this.name,
        position = this.position
    )
}