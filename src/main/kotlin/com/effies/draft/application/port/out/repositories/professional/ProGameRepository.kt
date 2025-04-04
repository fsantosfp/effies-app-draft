package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProGameEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface ProGameRepository: CrudRepository<ProGameEntity, String> {

    @EntityGraph(attributePaths = ["schedule", "schedule.league"])
    @Query("""
    SELECT g FROM ProGameEntity g
    WHERE g.schedule.startTime = :startTime
    AND g.isDone = false
    ORDER BY g.sequence ASC
""")
    fun findNextGameToPlay(
        @Param("startTime") startTime: LocalDateTime,
        pageable: Pageable
    ): List<ProGameEntity>

}