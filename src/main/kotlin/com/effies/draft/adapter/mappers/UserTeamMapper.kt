package com.effies.draft.adapter.mappers

import com.effies.draft.adapter.`in`.http.msg.UserTeamMsg
import com.effies.draft.adapter.out.persistence.UserTeamDto
import com.effies.draft.domains.UserTeam

fun UserTeamMsg.toDomain(userId: String): UserTeam{
    return UserTeam(
        teamId = this.teamId,
        userId = userId,
        name= this.name,
        coachName = this.coachName,
        code = this.code,
    )
}

fun UserTeam.toMsg(): UserTeamMsg {
    return UserTeamMsg(
        teamId = this.teamId,
        name= this.name,
        coachName = this.coachName,
        code = this.code,
        budget = this.budget,
        patrimony = this.patrimony,
        points = this.points
    )
}

fun UserTeam.toDto(): UserTeamDto{
    return UserTeamDto(
        teamId = this.teamId!!,
        userId = this.userId,
        name= this.name,
        coachName = this.coachName,
        code = this.code,
        budget = this.budget,
        patrimony = this.patrimony,
        points = this.points
    )
}

fun UserTeamDto.toDomain(): UserTeam{
    return UserTeam(
        teamId = this.teamId,
        userId = this.userId,
        name= this.name,
        coachName = this.coachName,
        code = this.code,
        budget = this.budget,
        patrimony = this.patrimony,
        points = this.points
    )
}
