package com.effies.draft.adapter.out.persistence.postgres

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "team")
data class TeamEntity(
    @Id
    val teamId: String,
    val userId: String,
    val name: String,
    val code: String
)
