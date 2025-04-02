package com.effies.draft.mappers.professional

import com.effies.draft.adapter.out.api.msg.ProPlayerResponse
import com.effies.draft.adapter.out.api.msg.ProTeamResponse
import com.effies.draft.adapter.out.api.msg.TeamsResponse
import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProPlayerEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProTeamEntity
import com.effies.draft.domains.RoleEnum
import com.effies.draft.domains.professional.ProPlayer
import com.effies.draft.domains.professional.ProTeam


fun ProTeamResponse.toEntity(league: ProLeagueEntity): ProTeamEntity {
    return ProTeamEntity(
        id = this.id,
        name = this.name,
        code = this.code,
        image = this.image,
        league = league
    )
}

fun ProPlayerResponse.toEntity(team: ProTeamEntity): ProPlayerEntity {
        return ProPlayerEntity(
            id = this.id,
            summonerName = this.summonerName,
            image = this.image,
            roleId = RoleEnum.fromDescription(this.role)!!,
            team = team
        )
}

fun Collection<ProPlayerResponse>.toEntity(team: ProTeamEntity): List<ProPlayerEntity> {
    return this.filter { player -> RoleEnum.fromDescription(player.role) != null }
        .map { player -> player.toEntity(team) }
}

fun ProPlayerEntity.toDomain(): ProPlayer{
    return ProPlayer(
        id = this.id,
        summonerName = this.summonerName,
        image = this.image,
        role = this.roleId,
    )
}

fun ProTeamEntity.toDomain(): ProTeam{
    return ProTeam(
        id = this.id,
        name = this.name,
        code = this.code,
        image = this.image,
        players = this.players.map { it.toDomain() }.toSet()
    )
}
