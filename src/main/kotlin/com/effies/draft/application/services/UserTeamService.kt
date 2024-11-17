package com.effies.draft.application.services

import com.effies.draft.adapter.`in`.http.exceptions.ValidatorException
import com.effies.draft.adapter.out.persistence.UserTeamDto
import com.effies.draft.application.port.out.UserTeamRepository
import com.effies.draft.application.validations.UserTeamValidator
import com.effies.draft.domains.UserTeam
import java.util.UUID

class UserTeamService(
    private val repository: UserTeamRepository
){

    fun create(userTeam: UserTeam): UserTeam {

        val result = UserTeamValidator().validate(userTeam)
        if(!result.valid) throw ValidatorException(result.errors)

        val userTeamDto = UserTeamDto(
            teamId = UUID.randomUUID().toString(),
            userId = userTeam.userId,
            name = userTeam.name,
            acronym = userTeam.acronym,
            coachName = userTeam.coachName
        )

        val teamSaved = repository.save(userTeamDto)

        return UserTeam(
            teamId = teamSaved.teamId,
            userId = teamSaved.userId,
            name = teamSaved.name,
            acronym = teamSaved.acronym,
            coachName = teamSaved.coachName
        )
    }
}