package com.effies.draft.adapter.out.scheduler

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.api.msg.ProPlayerResponse
import com.effies.draft.adapter.out.api.msg.ProTeamResponse
import com.effies.draft.application.port.out.repositories.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.ProTeamRepository
import com.effies.draft.mappers.professional.toEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExternalDataScheduler(
    private val lolClient: LegueOfLegendsExternalApi,
    private val leagueRepository: ProLeagueRepository,
    private val teamRepository: ProTeamRepository
) {

    @Scheduled(fixedDelay = 60_000)
    fun getInfo(){

        try {

            val leagues = lolClient.getLeague().leagues
            val teams = lolClient.getTeams()?.teams

            leagues.forEach {

                val league = it.toEntity()
                leagueRepository.save(league)

                teams
                    ?.filter { team -> team.homeLeague?.name == league.name }
                    ?.forEach { responseTeam ->
                        if( isActiveTeam(responseTeam) && hasPlayers(responseTeam.players) ){
                            val team = responseTeam.toEntity(league)
                            team.players.addAll(responseTeam.players!!.toEntity(team))
                            teamRepository.save(team)
                        }else{
                            teamRepository.deleteById(responseTeam.id)
                        }
                    }
            }

        }catch (e: Exception){
            println(e)
        }
    }
    private fun isActiveTeam(team: ProTeamResponse) = team.status != "archived" && team.homeLeague != null
    private fun hasPlayers(players: List<ProPlayerResponse>?) = !players.isNullOrEmpty()

}