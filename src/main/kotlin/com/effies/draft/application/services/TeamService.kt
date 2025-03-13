package com.effies.draft.application.services

import com.effies.draft.application.exceptions.AlreadyExistsException
import com.effies.draft.application.exceptions.NotFoundException
import com.effies.draft.application.exceptions.ValidatorException
import com.effies.draft.mappers.toDomain
import com.effies.draft.mappers.toEntity
import com.effies.draft.application.port.out.FinancialRepository
import com.effies.draft.application.port.out.ScoreRepository
import com.effies.draft.application.port.out.TeamRepository
import com.effies.draft.application.validations.TeamValidator
import com.effies.draft.domains.Financial
import com.effies.draft.domains.Score
import com.effies.draft.domains.Team
import com.effies.draft.domains.TeamStats
import java.util.UUID

class TeamService(
    private val teamRepository: TeamRepository,
    private val financialRepository: FinancialRepository,
    private val scoreRepository: ScoreRepository
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

            val score = Score(
                totalScore = 0.00,
                lastScore = 0.00,
                currentScore = 0.00
            ).toEntity(newTeam.teamId)

            return teamRepository.save(newTeam).also{
                financialRepository.save(financial)
                scoreRepository.save(score)
            }.toDomain()
        }

        throw AlreadyExistsException("A team for this user already exists.")
    }

    fun getById(userId: String): Team {
        val userTeam = teamRepository.findByUserId(userId) ?: throw NotFoundException("Resource not found.")
        return userTeam.toDomain()
    }

    fun getStats(userId: String, teamId: String): TeamStats {

        val team = this.getById(userId)

        if(!team.teamId.equals(teamId)) throw NotFoundException("Resource not found.")

        val financial = financialRepository.findByTeamId(teamId).toDomain()
        val score = scoreRepository.findByTeamId(teamId).toDomain()

        return TeamStats(
            teamName = team.name,
            financial = financial,
            score = score
        )

    }
}