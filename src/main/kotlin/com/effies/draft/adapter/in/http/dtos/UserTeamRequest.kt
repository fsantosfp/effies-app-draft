package com.effies.draft.adapter.`in`.http.dtos

import com.fasterxml.jackson.annotation.JsonProperty

data class UserTeamRequest(
    @JsonProperty("team_id")
    val teamId: String? = null,

    @JsonProperty("user_id")
    val userId: String,

    val name: String,

    @JsonProperty("coach_name")
    val coachName: String,

    val acronym: String
)
