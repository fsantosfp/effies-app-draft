package com.effies.draft.adapter.out.scheduler.tasks

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.api.msg.ProPlayerResponse
import com.effies.draft.adapter.out.api.msg.ProTeamResponse
import com.effies.draft.adapter.out.scheduler.SchedulerTask
import com.effies.draft.application.port.out.repositories.professional.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.professional.ProTeamRepository
import com.effies.draft.mappers.professional.toEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProTeamTask(
    private val lolClient: LegueOfLegendsExternalApi,
    private val leagueRepository: ProLeagueRepository,
    private val teamRepository: ProTeamRepository,
): SchedulerTask {

    private val logger = LoggerFactory.getLogger(this::class.java)
    override fun execute() {
        try {

            logger.info("Loading info about Teams")

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
            logger.error("Error when try to load team", e)
        }
    }

    private fun isActiveTeam(team: ProTeamResponse) = team.status != "archived" && team.homeLeague != null
    private fun hasPlayers(players: List<ProPlayerResponse>?) = !players.isNullOrEmpty()

}