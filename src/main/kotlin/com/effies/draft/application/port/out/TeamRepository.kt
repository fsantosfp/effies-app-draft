package com.effies.draft.application.port.out

import com.effies.draft.adapter.out.persistence.postgres.TeamEntity
import org.springframework.data.repository.CrudRepository

interface TeamRepository: CrudRepository<TeamEntity, String>{

    fun findByUserId(userId: String): TeamEntity?

}