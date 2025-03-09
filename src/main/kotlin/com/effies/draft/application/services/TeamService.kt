package com.effies.draft.application.services

import com.effies.draft.adapter.`in`.http.exceptions.AlreadyExistsException
import com.effies.draft.adapter.`in`.http.exceptions.NotFoundException
import com.effies.draft.adapter.`in`.http.exceptions.ValidatorException
import com.effies.draft.adapter.mappers.toDomain
import com.effies.draft.adapter.mappers.toEntity
import com.effies.draft.application.port.out.FinancialRepository
import com.effies.draft.application.port.out.TeamRepository
import com.effies.draft.application.validations.TeamValidator
import com.effies.draft.domains.Financial
import com.effies.draft.domains.Team
import java.util.UUID

class TeamService(
    private val teamRepository: TeamRepository,
    private val financialRepository: FinancialRepository
){

    fun create(team: Team): Team {

        if( teamRepository.findByUserId(team.userId) == null){
            val result = TeamValidator().validate(team)
            if(!result.valid) throw ValidatorException(result.errors)

            val newTeam = team.copy(
                teamId = UUID.randomUUID().toString()
            ).toEntity()

            val financial = Financial(
                budget = 100.00,
                patrimony = 0.00
            ).toEntity(newTeam.teamId)

            return teamRepository.save(newTeam).also{
                financialRepository.save(financial)
            }.toDomain()
        }

        throw AlreadyExistsException("A team for this user already exists.")
    }

    fun getById(userId:String): Team {
        val userTeam = teamRepository.findByUserId(userId) ?: throw NotFoundException("Resource not found this user.")
        return userTeam.toDomain()
    }
}