package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "pro_game_statistics")
class ProGameStatisticsEntity(

    @Id
    var id: String,

    var kills: Int = 0,
    var deaths: Int = 0,
    var assists: Int = 0,
    var creepScore: Int = 0,
    var inhibitors: Int = 0,
    var towers: Int = 0,
    var barons: Int = 0,
    var dragons: Int = 0,
    var totalGoldEarned: Int = 0,
    var killParticipation: Double = 0.0,
    var championDamageShare: Double = 0.0,
    var wardsPlaced: Int = 0,
    var wardsDestroyed: Int = 0,

    @ManyToOne
    @JoinColumn(name = "player_id")
    var proPlayer: ProPlayerEntity,

    @ManyToOne
    @JoinColumn(name = "game_id")
    var game: ProGameEntity

): BaseEntity()