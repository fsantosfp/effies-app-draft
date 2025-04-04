package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ProLeagueRepository: CrudRepository<ProLeagueEntity, String> {
    @Query("SELECT l FROM ProLeagueEntity l LEFT JOIN FETCH l.tournaments")
    fun findAllWithTournaments():List<ProLeagueEntity>

    @Query("SELECT l FROM ProLeagueEntity l LEFT JOIN FETCH l.schedule")
    fun findAllWithSchedules():List<ProLeagueEntity>
}