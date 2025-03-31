package com.effies.draft.adapter.out.scheduler

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.api.msg.ProTeamResponse
import com.effies.draft.application.port.out.repositories.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.ProPlayerRepository
import com.effies.draft.application.port.out.repositories.ProTeamRepository
import com.effies.draft.domains.RoleEnum
import com.effies.draft.mappers.professional.ToEntity
import com.effies.draft.mappers.draft.toEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ExternalDataScheduler(
    private val legueOfLegendsExternalApi: LegueOfLegendsExternalApi,
    private val teamRepository: ProTeamRepository,
    private val leagueRepository: ProLeagueRepository,
    private val playerRepository : ProPlayerRepository,
) {

    @Scheduled(fixedDelay = 60_000)
    fun getInfo(){

        try {
            val leagueResponse = legueOfLegendsExternalApi.getLeague()
            val teamResponse = legueOfLegendsExternalApi.getTeams()

            leagueResponse.leagues.forEach { leagueRepository.save(it.ToEntity()) }

            teamResponse?.teams
                ?.filter { it.name != "TBD"}
                ?.forEach { team ->
                    handlerTeam(team)
                    handlerPlayer(team)
                }

        }catch (e: Exception){
            println(e)
        }

    }

    private fun handlerTeam(team: ProTeamResponse){
        if( team.status != "archived" && team.homeLeague != null ){
            val leagueId = leagueRepository.findByName(team.homeLeague.name).id
            teamRepository.save(team.toEntity(leagueId))
        }else{
            teamRepository.deleteById(team.id)
        }
    }

    private fun handlerPlayer(team: ProTeamResponse){
        team.players?.forEach {

            val role = RoleEnum.fromDescription(it.role)
            if(role != null){
                playerRepository.save(it.toEntity(role.id, team.id))
            }
        }
    }

}