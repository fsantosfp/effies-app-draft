package com.effies.draft.adapter.`in`.http.configs

import com.effies.draft.application.port.out.FinancialRepository
import com.effies.draft.application.port.out.ScoreRepository
import com.effies.draft.application.port.out.TeamRepository
import com.effies.draft.application.services.TeamService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

    @Bean
    fun userTeamUseCase(
        teamRepository: TeamRepository,
        financialRepository: FinancialRepository,
        scoreRepository: ScoreRepository
    ): TeamService{
        return TeamService(
            teamRepository,
            financialRepository,
            scoreRepository
        )
    }
}