package com.effies.draft.domains.professional

data class ProTeams(
    val teams: List<ProTeam>
)

data class ProTeam(
    val id: String,
    val slug: String,
    val name: String,
    val code: String,
    val image: String,
    val status: String,
    val homeLeague: HomeLeague,
    val players: List<ProPlayer>
)

data class HomeLeague (
    val name: String,
    val region: String
)

data class ProPlayer(
    val id: String,
    val summonerName: String,
    val image: String,
    val role: String
)
