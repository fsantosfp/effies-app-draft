package com.effies.draft.mappers

import com.effies.draft.adapter.out.api.msg.ProLeague
import com.effies.draft.adapter.out.persistence.postgres.ProLeagueEntity

fun ProLeague.ToEntity(): ProLeagueEntity{
    return ProLeagueEntity(
        id = this.id,
        name = this.name,
        region = this.region
    )
}