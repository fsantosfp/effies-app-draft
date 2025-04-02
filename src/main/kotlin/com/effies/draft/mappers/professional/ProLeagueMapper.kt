package com.effies.draft.mappers.professional

import com.effies.draft.adapter.out.api.msg.ProLeague
import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import com.effies.draft.adapter.out.persistence.postgres.professional.ProTeamEntity
import com.effies.draft.domains.professional.HomeLeague

fun ProLeague.toEntity(): ProLeagueEntity {
    return ProLeagueEntity(
        id = this.id,
        name = this.name,
        region = this.region
    )
}

fun ProLeagueEntity.toDomain(): HomeLeague {
    return HomeLeague(
        name = this.name,
        region = this.region
    )
}