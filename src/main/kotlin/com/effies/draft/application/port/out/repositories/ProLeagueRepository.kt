package com.effies.draft.application.port.out.repositories

import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import org.springframework.data.repository.CrudRepository

interface ProLeagueRepository: CrudRepository<ProLeagueEntity, String> {
    fun findByName(name: String): ProLeagueEntity
}