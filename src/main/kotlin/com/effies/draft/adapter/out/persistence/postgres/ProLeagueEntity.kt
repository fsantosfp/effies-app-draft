package com.effies.draft.adapter.out.persistence.postgres

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pro_league")
data class ProLeagueEntity (
    @Id
    val id: String,
    val name: String,
    val region: String
)