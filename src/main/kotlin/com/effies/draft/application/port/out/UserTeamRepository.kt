package com.effies.draft.application.port.out

import com.effies.draft.adapter.out.persistence.postgres.UserTeamEntity
import org.springframework.data.repository.CrudRepository

interface UserTeamRepository: CrudRepository<UserTeamEntity, String>{

    fun findByUserId(userId: String): UserTeamEntity?

}