package com.effies.draft.mappers.professional

import com.effies.draft.adapter.out.api.msg.ProGameResponse
import com.effies.draft.adapter.out.persistence.postgres.professional.ProGameEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProScheduleEntity
import com.effies.draft.domains.TeamSideEnum


fun Collection<ProGameResponse>.toEntity(schedule: ProScheduleEntity): List<ProGameEntity>{
    return this.map { it.toEntity(schedule) }
}

fun ProGameResponse.toEntity(schedule: ProScheduleEntity): ProGameEntity{
    return ProGameEntity(
        id = this.id,
        sequence = this.number,
        teamBlueId = this.teams.first { it.side == TeamSideEnum.blue.name }.id,
        teamRedId = this.teams.first{ it.side == TeamSideEnum.red.name }.id,
        schedule = schedule
    )
}