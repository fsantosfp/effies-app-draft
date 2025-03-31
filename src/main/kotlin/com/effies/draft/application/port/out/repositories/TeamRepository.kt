package com.effies.draft.application.port.out.repositories

import com.effies.draft.adapter.out.persistence.postgres.draft.TeamEntity
import org.springframework.data.repository.CrudRepository

interface TeamRepository: CrudRepository<TeamEntity, String>{

    fun findByUserId(userId: String): TeamEntity?

}