package com.effies.draft.application.services

import com.effies.draft.adapter.`in`.http.exceptions.AlreadyExistsException
import com.effies.draft.adapter.`in`.http.exceptions.NotFoundException
import com.effies.draft.adapter.`in`.http.exceptions.ValidatorException
import com.effies.draft.adapter.mappers.toDomain
import com.effies.draft.adapter.mappers.toDto
import com.effies.draft.application.port.out.UserTeamRepository
import com.effies.draft.application.validations.UserTeamValidator
import com.effies.draft.domains.UserTeam
import java.util.UUID

class UserTeamService(
    private val repository: UserTeamRepository
){

    fun create(userTeam: UserTeam): UserTeam {

        val teamExists = repository.findByUserId(userTeam.userId)

        if( teamExists == null){
            val result = UserTeamValidator().validate(userTeam)
            if(!result.valid) throw ValidatorException(result.errors)

            val newTeam = userTeam.copy(
                teamId = UUID.randomUUID().toString(),
                budget = 100.00
            ).toDto()

            return repository.save(newTeam).toDomain()
        }

        throw AlreadyExistsException("A team for this user already exists.")

    }

    fun getById(userId:String): UserTeam {
        val userTeam = repository.findByUserId(userId) ?: throw NotFoundException("Resource not found this user.")

        return userTeam.toDomain()
    }
}