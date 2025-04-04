package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "pro_schedule")
class ProScheduleEntity(

    @Id
    var id: String,

    var startTime: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "league_id")
    var league: ProLeagueEntity,

    @OneToMany(mappedBy = "schedule", cascade = [CascadeType.ALL], orphanRemoval = true)
    var games: MutableList<ProGameEntity> = mutableListOf()
): BaseEntity()
