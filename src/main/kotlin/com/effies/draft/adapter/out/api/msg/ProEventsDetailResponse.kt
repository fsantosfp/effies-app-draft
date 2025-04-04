package com.effies.draft.adapter.out.api.msg

data class ProEventDetailData(
    val data: ProEventResponse
)

data class ProEventResponse(
    val event: ProMatchResponse
)

data class ProMatchResponse(
    val id: String,
    val match: ProGamesResponse,
)

data class ProGamesResponse(
    val games: List<ProGameResponse>
)

data class ProGameResponse(
    val number: Int,
    val id: String,
    val teams:List<ProMatchTeamResponse>
)

data class ProMatchTeamResponse(
    val id: String,
    val side:String
)