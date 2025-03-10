package com.effies.draft.application.port.out

import com.effies.draft.adapter.out.persistence.postgres.ScoreEntity
import org.springframework.data.repository.CrudRepository

interface ScoreRepository: CrudRepository<ScoreEntity, String> {
}