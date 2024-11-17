package com.effies.draft.adapter.`in`.http.configs

import com.effies.draft.application.port.out.UserTeamRepository
import com.effies.draft.application.services.UserTeamService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceConfig {

    @Bean
    fun userTeamUseCase(
        userTeamRepository: UserTeamRepository
    ): UserTeamService{
        return UserTeamService(
            userTeamRepository
        )
    }
}