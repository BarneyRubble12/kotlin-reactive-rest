package com.bdrk.rest.reactive.model

import org.springframework.data.annotation.Id
import java.time.LocalDate

data class HealthRecord(
    @Id var id: Long?,
    var profileId: Long?,
    var temperature: Double,
    var bloodPressure: Double,
    var heartRate: Double,
    var date: LocalDate
)