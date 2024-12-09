package com.effies.draft.adapter.out.persistence.postgres

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_teams")
data class UserTeamEntity(
    @Id
    val teamId: String,
    val userId: String,
    val name: String,
    val coachName: String,
    val code: String,
    val budget: Double,
    val patrimony: Double,
    val points: Double
)
