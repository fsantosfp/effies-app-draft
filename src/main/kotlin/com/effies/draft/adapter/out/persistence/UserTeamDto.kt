package com.effies.draft.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_teams")
data class UserTeamDto(
    @Id
    val teamId: String,
    val userId: String,
    val name: String,
    val coachName: String,
    val acronym: String
)
