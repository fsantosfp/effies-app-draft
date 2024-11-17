package com.effies.draft.domains

data class UserTeam (
    val teamId: String? = null,
    val userId: String,
    val name: String,
    val coachName: String,
    val acronym: String
)