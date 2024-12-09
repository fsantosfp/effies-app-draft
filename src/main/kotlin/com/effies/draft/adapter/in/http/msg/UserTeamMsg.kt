package com.effies.draft.adapter.`in`.http.msg

import com.fasterxml.jackson.annotation.JsonProperty

data class UserTeamMsg(
    @JsonProperty("team_id")
    val teamId: String? = null,

    val name: String,

    @JsonProperty("coach_name")
    val coachName: String,

    val code: String,

    val budget: Double = 0.00,

    val patrimony: Double = 0.00,

    val points: Double = 0.00
)
