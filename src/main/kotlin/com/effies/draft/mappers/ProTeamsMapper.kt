package com.effies.draft.mappers

import com.effies.draft.adapter.out.api.msg.ProPlayerResponse
import com.effies.draft.adapter.out.api.msg.ProTeamResponse
import com.effies.draft.adapter.out.persistence.postgres.ProPlayerEntity
import com.effies.draft.adapter.out.persistence.postgres.ProTeamEntity


fun ProTeamResponse.toEntity(leagueId: String): ProTeamEntity {
    return ProTeamEntity(
        id = this.id,
        name = this.name,
        code = this.code,
        image = this.image,
        leagueId = leagueId
    )
}

fun ProPlayerResponse.toEntity(roleId: Int, teamId: String): ProPlayerEntity {
    return ProPlayerEntity(
        id = this.id,
        summonerName = this.summonerName,
        image = this.image,
        roleId = roleId,
        teamId = teamId
    )
}