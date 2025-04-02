package com.effies.draft.domains.professional

data class ProTeams(
    val teams: List<ProTeam>
)

data class ProTeam(
    val id: String,
    val name: String,
    val code: String,
    val image: String,
    val players: Set<ProPlayer>? = null
)

data class HomeLeague (
    val name: String,
    val region: String
)

data class ProPlayer(
    val id: String,
    val summonerName: String,
    val image: String,
    val role: Int
)
