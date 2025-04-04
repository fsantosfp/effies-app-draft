package com.effies.draft.adapter.`in`.http.configs

import com.effies.draft.application.port.out.repositories.draft.FinancialRepository
import com.effies.draft.application.port.out.repositories.draft.ScoreRepository
import com.effies.draft.application.port.out.repositories.draft.TeamRepository
import com.effies.draft.application.port.out.repositories.professional.ProLeagueRepository
import com.effies.draft.application.port.out.repositories.professional.ProTeamRepository
import com.effies.draft.application.services.MarketService
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

    @Bean
    fun marketUseCase(
        proTeamRepository: ProTeamRepository,
        proLeagueRepository: ProLeagueRepository
    ): MarketService{
        return MarketService(
            proTeamRepository,
            proLeagueRepository
        )
    }
}