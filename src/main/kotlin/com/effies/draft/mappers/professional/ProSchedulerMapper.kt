package com.effies.draft.mappers.professional

import com.effies.draft.adapter.out.api.msg.ScheduleEventResponse
import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProScheduleEntity
import java.time.LocalDateTime

fun Collection<ScheduleEventResponse>.toEntity(league: ProLeagueEntity): List<ProScheduleEntity> {
    return this.map { it.toEntity(league) }
}

fun ScheduleEventResponse.toEntity(league: ProLeagueEntity): ProScheduleEntity{
    return ProScheduleEntity(
        startTime = LocalDateTime.parse(this.startTime.removeSuffix("Z")),
        id = this.match.id,
        league = league
    )
}