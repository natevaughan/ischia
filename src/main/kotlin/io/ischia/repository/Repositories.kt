package io.ischia.repository

import io.ischia.domain.User
import io.ischia.domain.UserDAO
import org.springframework.data.repository.CrudRepository

/**
 * This tells hibernate to wire up the DAO
 */
interface UserRepository: UserDAO, CrudRepository<User, Long>