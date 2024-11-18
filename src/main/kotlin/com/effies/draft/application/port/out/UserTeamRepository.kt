package com.effies.draft.application.port.out

import com.effies.draft.adapter.out.persistence.UserTeamDto
import org.springframework.data.repository.CrudRepository

interface UserTeamRepository: CrudRepository<UserTeamDto, String>{

    fun findByUserId(userId: String): UserTeamDto?

}