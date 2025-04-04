package com.effies.draft.adapter.out.scheduler.tasks

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.scheduler.SchedulerTask
import com.effies.draft.application.port.out.repositories.professional.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.professional.ProTournamentsRepository
import com.effies.draft.mappers.professional.toEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProTournamentTask(
    private val lolClient: LegueOfLegendsExternalApi,
    private val leagueRepository: ProLeagueRepository,
    private val tournamentRepository: ProTournamentsRepository
): SchedulerTask{

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute() {

        logger.info("Loading info about Tournaments")

        try {
            val leagues = leagueRepository.findAll()

            leagues.forEach { league ->
                val tournaments = lolClient.getTournaments(league.id).leagues[0].tournaments
                tournaments.forEach {
                    tournamentRepository.save(it.toEntity(league))
                }
            }

        }catch (e: Exception){
            logger.error("Error when try to load tournaments", e)
        }
    }
}