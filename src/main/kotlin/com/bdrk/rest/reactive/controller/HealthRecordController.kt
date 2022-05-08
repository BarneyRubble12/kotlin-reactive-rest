package com.bdrk.rest.reactive.controller

import com.bdrk.rest.reactive.model.AverageHealthStatus
import com.bdrk.rest.reactive.model.HealthRecord
import com.bdrk.rest.reactive.model.Profile
import com.bdrk.rest.reactive.repository.HealthRecordRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HealthRecordController(val repository: HealthRecordRepository) {

    @PostMapping("/health/{profileId}/record")
    fun saveHealthRecord(
        @PathVariable("profileId") profileId: Long,
        @RequestBody record: HealthRecord): Mono<HealthRecord> {
        return repository.save(
            HealthRecord(
                null,
                profileId,
                record.temperature,
                record.bloodPressure,
                record.heartRate,
                record.date
            )
        )
    }

    @GetMapping("/health/{profileId}/avg")
    fun fetchHealthRecordAverage(
        @PathVariable("profileId") profileId: Long): Mono<AverageHealthStatus> {
        return repository.findByProfileId(profileId)
            .reduce(
                AverageHealthStatus()
            ) { s, r ->
                AverageHealthStatus(
                    s.cnt + 1,
                    s.bloodPressure + r.bloodPressure,
                    s.temperature + r.temperature,
                    s.heartRate + r.heartRate
                )
            }
            .map { s ->
                AverageHealthStatus(
                    s.cnt,
                    if (s.cnt != 0) s.bloodPressure / s.cnt else 0.0,
                    if (s.cnt != 0) s.temperature / s.cnt else 0.0,
                    if (s.cnt != 0) s.heartRate / s.cnt else 0.0,
                )
            }
    }
}