package com.effies.draft.application.port.out.repositories

import com.effies.draft.adapter.out.persistence.postgres.ScoreEntity
import org.springframework.data.repository.CrudRepository

interface ScoreRepository: CrudRepository<ScoreEntity, String> {
    fun findByTeamId(teamId: String): ScoreEntity
}