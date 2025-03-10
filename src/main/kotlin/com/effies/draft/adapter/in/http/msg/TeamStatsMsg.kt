package com.effies.draft.adapter.`in`.http.msg

data class TeamStatsMsg(
    val teamName: String,
    val financial: FinancialMsg,
    val score: ScoreMsg
)
