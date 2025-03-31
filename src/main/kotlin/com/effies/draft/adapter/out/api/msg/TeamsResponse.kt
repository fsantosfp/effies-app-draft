package com.effies.draft.adapter.out.api.msg

data class TeamsResponse(
    val data: ProTeamsResponse
)

data class ProTeamsResponse (
    val teams: List<ProTeamResponse>?
)

data class ProTeamResponse(
    val id: String,
    val slug: String,
    val name: String,
    val code: String,
    val image: String,
    val status: String,
    val homeLeague: HomeLeagueResponse?,
    val players: List<ProPlayerResponse>?
)

data class HomeLeagueResponse (
    val name: String,
    val region: String
)

data class ProPlayerResponse(
    val id: String,
    val summonerName: String,
    val image: String,
    val role: String
)
