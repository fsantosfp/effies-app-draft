package com.effies.draft.mappers

import com.effies.draft.adapter.`in`.http.msg.TeamMsg
import com.effies.draft.adapter.`in`.http.msg.TeamStatsMsg
import com.effies.draft.adapter.out.persistence.postgres.TeamEntity
import com.effies.draft.domains.Team
import com.effies.draft.domains.TeamStats

fun TeamMsg.toDomain(userId: String): Team{
    return Team(
        teamId = this.teamId,
        userId = userId,
        name= this.name,
        code = this.code,
    )
}

fun Team.toMsg(): TeamMsg {
    return TeamMsg(
        teamId = this.teamId,
        name= this.name,
        code = this.code
    )
}

fun Team.toEntity(): TeamEntity {
    return TeamEntity(
        teamId = this.teamId!!,
        userId = this.userId,
        name= this.name,
        code = this.code
    )
}

fun TeamEntity.toDomain(): Team{
    return Team(
        teamId = this.teamId,
        userId = this.userId,
        name= this.name,
        code = this.code
    )
}

fun TeamStats.toMsg(): TeamStatsMsg{
    return TeamStatsMsg(
        teamName = this.teamName,
        financial = this.financial.toMsg(),
        score = this.score.toMsg()
    )
}
