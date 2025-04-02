package com.effies.draft.domains.draft

import com.effies.draft.domains.professional.HomeLeague
import com.effies.draft.domains.professional.ProTeam

data class MarketData(
    val teams: List<ProTeam>,
    val league: HomeLeague,
    val score: ProPlayerScore? = null,
    val value: ProPlayerValue? = null
)
