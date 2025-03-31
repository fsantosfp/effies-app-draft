package com.effies.draft.application.port.out.repositories

import com.effies.draft.adapter.out.persistence.postgres.ProPlayerEntity
import org.springframework.data.repository.CrudRepository

interface ProPlayerRepository: CrudRepository<ProPlayerEntity, String> {}