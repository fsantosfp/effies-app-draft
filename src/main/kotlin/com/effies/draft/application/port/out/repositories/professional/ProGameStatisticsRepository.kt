package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProGameStatisticsEntity
import org.springframework.data.repository.CrudRepository

interface ProGameStatisticsRepository: CrudRepository<ProGameStatisticsEntity, String> {
}