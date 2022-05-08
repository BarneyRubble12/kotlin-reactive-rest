package com.bdrk.rest.reactive.repository

import com.bdrk.rest.reactive.model.HealthRecord
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface HealthRecordRepository: ReactiveCrudRepository<HealthRecord, Long> {

    @Query("select * from health_record where profile_id = :profileId")
    fun findByProfileId(profileId: Long): Flux<HealthRecord>

}