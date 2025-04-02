package com.effies.draft.adapter.out.persistence.postgres.professional

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pro_league")
data class ProLeagueEntity (
    @Id
    val id: String,
    val name: String,
    val region: String,

    @OneToMany(mappedBy = "league", orphanRemoval = true)
    val team: MutableList<ProTeamEntity> = mutableListOf()
)