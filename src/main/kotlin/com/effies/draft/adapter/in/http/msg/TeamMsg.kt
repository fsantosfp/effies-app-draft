package com.effies.draft.adapter.`in`.http.msg

import com.fasterxml.jackson.annotation.JsonProperty

data class TeamMsg(
    @JsonProperty("team_id")
    val teamId: String? = null,
    val name: String,
    val code: String
)
