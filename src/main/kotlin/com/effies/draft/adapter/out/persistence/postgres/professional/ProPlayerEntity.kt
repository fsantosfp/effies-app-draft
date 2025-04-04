package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pro_player")
class ProPlayerEntity(

    @Id
    var id: String,

    var summonerName: String,

    var image: String,

    var roleId: Int,

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    var team: ProTeamEntity,

    @OneToMany(mappedBy = "proPlayer")
    var statistics: MutableList<ProGameStatisticsEntity> = mutableListOf()
): BaseEntity()
