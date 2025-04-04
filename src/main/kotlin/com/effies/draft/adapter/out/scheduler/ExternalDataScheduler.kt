package com.effies.draft.adapter.out.scheduler

import com.effies.draft.adapter.out.scheduler.tasks.*
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExternalDataScheduler (
    private val scheduleTask: ProScheduleTask,
    private val teamTask: ProTeamTask,
    private val tournamentTask: ProTournamentTask,
    private val matchTask: ProGameTask,
    private val gameStaticsTask: GameStaticsTask
    ){
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(fixedDelay = 120_000)
    fun executeOnceADay(){
        logger.info("Starting Scheduler - Cache data")

            tournamentTask.execute()
            teamTask.execute()
            scheduleTask.execute()
            matchTask.execute()
    }

    @Scheduled(fixedDelay = 60_000)
    fun executeEveryMinute(){
        logger.info("Starting Scheduler - live data")
        gameStaticsTask.execute()
    }


}