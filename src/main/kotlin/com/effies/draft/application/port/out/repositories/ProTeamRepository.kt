package com.effies.draft.application.port.out.repositories

import com.effies.draft.adapter.out.persistence.postgres.ProTeamEntity
import org.springframework.data.repository.CrudRepository

interface ProTeamRepository: CrudRepository<ProTeamEntity, String>{
}