package com.effies.draft.adapter.out.persistence.postgres.professional

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "pro_player")
data class ProPlayerEntity(

    @Id
    val id: String,
    val summonerName: String,
    val image: String,
    val roleId: Int,

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    val team: ProTeamEntity
)
