package com.bdrk.rest.reactive.controller

import com.bdrk.rest.reactive.model.Profile
import com.bdrk.rest.reactive.repository.ProfileRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ProfileController(val repository: ProfileRepository) {

    @PostMapping("/profile")
    fun save(@RequestBody profile: Profile): Mono<Profile> {
        return repository.save(profile)
    }

}