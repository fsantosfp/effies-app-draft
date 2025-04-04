package com.effies.draft.adapter.out.scheduler.tasks

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.persistence.postgres.professional.ProGameEntity
import com.effies.draft.adapter.out.scheduler.SchedulerTask
import com.effies.draft.application.port.out.repositories.professional.ProGameRepository
import com.effies.draft.application.port.out.repositories.professional.ProLeagueRepository
import com.effies.draft.mappers.professional.toEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProGameTask(
    private val lolClient: LegueOfLegendsExternalApi,
    private val leagueRepository: ProLeagueRepository,
    private val gameRepository: ProGameRepository
): SchedulerTask{

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun execute() {

        logger.info("Loading info about Games")

        try {
            leagueRepository.findAllWithSchedules().forEach { league ->

                league.schedule.forEach { schedule ->

                    val event = lolClient.getEventsByLeague(
                        leagueId = league.id,
                        matchId = schedule.id
                    ).event

                    val games: List<ProGameEntity> = event.match.games.toEntity(schedule)
                    gameRepository.saveAll(games)

                }
            }
        }catch (e: Exception){
            logger.error("Error when try to load match", e)
        }
    }
}