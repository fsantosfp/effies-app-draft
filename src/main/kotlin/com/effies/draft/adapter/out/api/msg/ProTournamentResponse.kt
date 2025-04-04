package com.effies.draft.adapter.out.api.msg

data class ProTournamentsResponse(
    val data: ProLeaguesTournamentResponse
)

data class ProLeaguesTournamentResponse(
    val leagues: List<ProLeagueTournamentResponse>
)

data class ProLeagueTournamentResponse(
    val tournaments:List<ProTournamentResponse>
)

data class ProTournamentResponse(
    val id: String,
    val startDate: String,
    val endDate: String
)
