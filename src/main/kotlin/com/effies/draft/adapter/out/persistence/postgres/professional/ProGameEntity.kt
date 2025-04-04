package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pro_game")
class ProGameEntity(
    @Id
    var id: String,

    var sequence: Int,

    val teamBlueId: String,

    val teamRedId: String,

    val isDone: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    var schedule: ProScheduleEntity,

    @OneToMany(mappedBy = "game")
    val statistics: MutableList<ProGameStatisticsEntity> = mutableListOf()
): BaseEntity()
