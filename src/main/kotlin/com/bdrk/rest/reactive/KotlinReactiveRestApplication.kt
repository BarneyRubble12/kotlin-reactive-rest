package com.bdrk.rest.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinReactiveRestApplication

fun main(args: Array<String>) {
    runApplication<KotlinReactiveRestApplication>(*args)
}
