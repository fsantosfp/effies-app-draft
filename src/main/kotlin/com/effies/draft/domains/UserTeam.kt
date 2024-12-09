package com.effies.draft.domains

data class UserTeam (
    val teamId: String? = null,
    val userId: String,
    val name: String,
    val coachName: String,
    val code: String,
    val budget: Double = 0.00,
    val patrimony: Double = 0.00,
    val points: Double = 0.00
)