package com.bdrk.rest.reactive.controller

import com.bdrk.rest.reactive.model.Profile
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import java.time.LocalDateTime

@SpringBootTest
internal class ProfileControllerTest {

    @Autowired
    lateinit var controller: ProfileController
    @Autowired
    lateinit var mapper: ObjectMapper

    lateinit var client: WebTestClient
    lateinit var profile: String

    @BeforeEach
    fun setup() {
        client = WebTestClient.bindToController(controller).build()
        profile = mapper.writeValueAsString(
            Profile(null, "kotlin", "reactive", LocalDateTime.now()))
    }

    @Test
    fun whenRequestProfile_thenStatusShouldBeOk() {
        client
            .post()
            .uri("/profile/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(profile)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun whenRequestProfile_thenProfileIdShouldntBeNull() {
        client
            .post()
            .uri("/profile")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(profile)
            .exchange()
            .expectBody()
            .jsonPath("$.id")
            .isNotEmpty
    }

}