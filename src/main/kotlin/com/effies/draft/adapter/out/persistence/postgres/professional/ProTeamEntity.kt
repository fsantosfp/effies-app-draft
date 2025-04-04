package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "pro_team")
class ProTeamEntity(
    @Id
    var id: String,

    var name: String,
    var code: String,
    var image: String,

    @ManyToOne()
    @JoinColumn(name = "league_id")
    var league: ProLeagueEntity,

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    var players: MutableList<ProPlayerEntity> = mutableListOf()
): BaseEntity()