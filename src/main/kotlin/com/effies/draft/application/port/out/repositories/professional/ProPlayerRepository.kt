package com.effies.draft.application.port.out.repositories.professional

import com.effies.draft.adapter.out.persistence.postgres.professional.ProPlayerEntity
import org.springframework.data.repository.CrudRepository

interface ProPlayerRepository: CrudRepository<ProPlayerEntity, String> {}