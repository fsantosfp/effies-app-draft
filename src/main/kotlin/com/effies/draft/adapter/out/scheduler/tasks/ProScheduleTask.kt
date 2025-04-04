package com.effies.draft.adapter.out.scheduler.tasks

import com.effies.draft.adapter.out.api.LegueOfLegendsExternalApi
import com.effies.draft.adapter.out.api.msg.ScheduleInfoResponse
import com.effies.draft.adapter.out.persistence.postgres.professional.ProLeagueEntity
import com.effies.draft.adapter.out.scheduler.SchedulerTask
import com.effies.draft.application.port.out.repositories.professional.ProGameRepository
import com.effies.draft.application.port.out.repositories.professional.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.professional.ProScheduleRepository
import com.effies.draft.mappers.professional.toEntity
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class ProScheduleTask(
    private val lolClient: LegueOfLegendsExternalApi,
    private val leagueRepository: ProLeagueRepository,
    private val scheduleRepository: ProScheduleRepository
): SchedulerTask {

    private val logger = LoggerFactory.getLogger(this::class.java)
    private lateinit var minDate: LocalDate
    private lateinit var maxDate: LocalDate
    private lateinit var league: ProLeagueEntity
    private var page: String? = null

    override fun execute() {

        logger.info("Loading info about Schedule")

        try {
            val leagues = leagueRepository.findAllWithTournaments()

            leagues.forEach { league ->

                this.league = league

                    if(league.id == "113470291645289904"){

                    val dates = league.tournaments
                            .map{ Pair(it.startDate, it.endDate) }
                            .flatMap { it.toList() }

                    this.minDate = dates.minBy { it }
                    this.maxDate = dates.maxBy { it }

                    val schedule = getSchedule(league)
                        .takeIf { it.events.isNotEmpty() } ?: return

                    navigateIntoPages(shouldGoToPreviousPage(schedule))
                }
            }

        }catch (e: Exception){
            logger.error("Error when try to load Schedule for league ${this.league.id} and page ${this.page}", e)
        }

    }

    private fun shouldGoToPreviousPage(schedule: ScheduleInfoResponse): ScheduleInfoResponse{
        this.page = schedule.pages.older
        val olderEvent = schedule.events
            .map {it.startTime.toLocalDate()}
            .minBy{ it }

        if((this.minDate < olderEvent) && this.page != null){
            shouldGoToPreviousPage(getSchedule(this.league, this.page!!))
        }
        return schedule
    }

    private fun navigateIntoPages(response: ScheduleInfoResponse){
        scheduleRepository.saveAll(response.events.toEntity(this.league))
        shouldGoToNexPage(response)
    }

    private fun shouldGoToNexPage(schedule: ScheduleInfoResponse){
        this.page = schedule.pages.newer
        val newerEvent = schedule.events
            .map { it.startTime.toLocalDate() }
            .maxBy{ it }

        if((this.maxDate > newerEvent) && this.page != null){
            navigateIntoPages(getSchedule(this.league, this.page!!))
        }
    }

    private fun getSchedule(league: ProLeagueEntity):ScheduleInfoResponse = lolClient.getSchedule(league.id).schedule

    private fun getSchedule(league: ProLeagueEntity, page:String):ScheduleInfoResponse = lolClient.getSchedule(league.id, page).schedule

    private fun String.toLocalDate(): LocalDate{
        val onlyDate = this.split("T").first()
        return LocalDate.parse(onlyDate)
    }
}