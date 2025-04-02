package com.effies.draft.domains.draft

data class MarketPlayers(
    val players: List<ProPlayerMarket>
)

data class ProPlayerMarket(
    val name: String,
    val image: String,
    val role: String,
    val team: ProPlayerTeam,
    val score: ProPlayerScore,
    val value: ProPlayerValue
)

data class ProPlayerTeam(
    val name: String,
    val image: String,
    val homeLeague: String,
    val code: String,
)

data class ProPlayerScore(
    val last: Double,
    val average: Double
)

data class ProPlayerValue(
    val price: Double,
    val variation: Double
)
