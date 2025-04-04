package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pro_league")
class ProLeagueEntity(
    @Id
    var id: String,

    var name: String,

    var region: String,

    @OneToMany(mappedBy = "league", orphanRemoval = true)
    var team: MutableList<ProTeamEntity> = mutableListOf(),

    @OneToMany(mappedBy = "league", orphanRemoval = true)
    var tournaments: MutableList<ProTournamentEntity> = mutableListOf(),

    @OneToMany(mappedBy = "league", orphanRemoval = true)
    var schedule: MutableList<ProScheduleEntity> = mutableListOf()
): BaseEntity()