package com.effies.draft.adapter.out.persistence.postgres.professional

import com.effies.draft.adapter.out.persistence.postgres.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name= "pro_tournament")
class ProTournamentEntity(

    @Id
    var id: String,

    var startDate: LocalDate,

    var endDate: LocalDate,

    @ManyToOne
    @JoinColumn(name = "league_id")
    var league: ProLeagueEntity

): BaseEntity()
