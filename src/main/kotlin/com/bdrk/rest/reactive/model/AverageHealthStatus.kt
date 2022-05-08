package com.bdrk.rest.reactive.model

data class AverageHealthStatus(
    var cnt: Int = 0,
    var bloodPressure: Double = 0.0,
    var temperature: Double = 0.0,
    var heartRate: Double = 0.0
)
