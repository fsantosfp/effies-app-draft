package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProTeamEntity
import org.springframework.data.repository.CrudRepository

interface ProTeamRepository: CrudRepository<ProTeamEntity, String>{
    fun findAllByLeagueId(leagueId: String): List<ProTeamEntity>
}
