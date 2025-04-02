package com.effies.draft.adapter.`in`.http.msg

data class MarketPlayersMsg(
    val players: List<ProPlayerMsg>
)

data class ProPlayerMsg(
    val name: String,
    val image: String,
    val role: String,
    val team: ProPlayerTeamMsg,
    val score: ProPlayerScoreMsg,
    val value: ProPlayerValueMsg
)

data class ProPlayerTeamMsg(
    val name: String,
    val image: String,
    val homeLeague: String,
    val code: String,
)

data class ProPlayerScoreMsg(
    val last: Double,
    val average: Double
)

data class ProPlayerValueMsg(
    val price: Double,
    val variation: Double
)