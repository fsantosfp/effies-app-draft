package com.effies.draft.mappers.professional

import com.effies.draft.adapter.out.api.msg.ProTournamentResponse
import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProTournamentEntity
import java.time.LocalDate

fun ProTournamentResponse.toEntity(league: ProLeagueEntity): ProTournamentEntity{
    return  ProTournamentEntity(
        id = this.id,
        startDate = LocalDate.parse(this.startDate),
        endDate = LocalDate.parse(this.startDate),
        league = league
    )
}
