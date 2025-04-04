package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProScheduleEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface ProScheduleRepository: CrudRepository<ProScheduleEntity, String> {
    @EntityGraph(attributePaths = ["games"])
    @Query("SELECT s FROM ProScheduleEntity s WHERE s.startTime = :startTime")
    fun findWithGamesByStartTime(@Param("startTime") startTime: LocalDateTime): ProScheduleEntity?
}