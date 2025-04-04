package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProTournamentEntity
import org.springframework.data.repository.CrudRepository

interface ProTournamentsRepository: CrudRepository<ProTournamentEntity, String> {
}