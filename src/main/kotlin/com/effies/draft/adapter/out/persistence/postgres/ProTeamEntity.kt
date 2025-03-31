package com.effies.draft.adapter.out.persistence.postgres

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pro_team")
data class ProTeamEntity (
    @Id
    val id: String,
    val name: String,
    val code: String,
    val image: String,
    val leagueId: String
)