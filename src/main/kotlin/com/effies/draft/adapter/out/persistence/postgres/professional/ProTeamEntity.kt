package com.effies.draft.adapter.out.persistence.postgres.professional

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "pro_team")
data class ProTeamEntity (
    @Id
    val id: String,
    val name: String,
    val code: String,
    val image: String,

    @ManyToOne()
    @JoinColumn(name = "league_id")
    val league: ProLeagueEntity,

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    val players: MutableList<ProPlayerEntity> = mutableListOf()
)