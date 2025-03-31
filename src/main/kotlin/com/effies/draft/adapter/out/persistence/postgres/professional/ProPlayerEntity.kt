package com.effies.draft.adapter.out.persistence.postgres.professional

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pro_player")
data class ProPlayerEntity(

    @Id
    val id: String,
    val summonerName: String,
    val image: String,
    val roleId: Int,
    val teamId: String
)
