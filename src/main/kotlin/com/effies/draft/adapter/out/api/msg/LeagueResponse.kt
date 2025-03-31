package com.effies.draft.adapter.out.api.msg

data class ProLeagueResponse(
    val data: ProLeaguesResponse
)

data class ProLeaguesResponse(
    val leagues: List<ProLeague>
)

data class ProLeague(
    val id: String,
    val name: String,
    val region: String
)
