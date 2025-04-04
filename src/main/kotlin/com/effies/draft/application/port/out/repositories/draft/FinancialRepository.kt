package com.effies.draft.application.port.out.repositories.draft

import com.effies.draft.adapter.out.persistence.postgres.draft.FinancialEntity
import org.springframework.data.repository.CrudRepository

interface FinancialRepository: CrudRepository<FinancialEntity, String> {
    fun findByTeamId(teamId: String): FinancialEntity
}