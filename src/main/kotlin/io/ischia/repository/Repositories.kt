package io.ischia.repository

import io.ischia.domain.Message
import io.ischia.domain.ActivityDAO
import io.ischia.domain.User
import io.ischia.domain.UserDAO
import org.springframework.data.repository.CrudRepository

/**
 * The Repositories below tie together our annotation-less DAOs and Hibernate's
 * CrudRepository. This is a trick that tells Hibernate to wire up our DAOs
 * as if they had been annotated with @Repository and keeps Spring separate from
 * core business logic
 */
interface UserRepository: UserDAO, CrudRepository<User, Long>
interface ActivityRepository: ActivityDAO, CrudRepository<Message, String>