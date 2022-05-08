package com.bdrk.rest.reactive.repository

import com.bdrk.rest.reactive.model.Profile
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository: ReactiveCrudRepository<Profile, Long> {
}