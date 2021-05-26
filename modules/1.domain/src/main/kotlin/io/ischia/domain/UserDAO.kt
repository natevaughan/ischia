package io.ischia.domain

import java.util.Optional

/**
 * Example Data Access Object (DAO)
 *
 * If method names follow hibernate naming conventions, these methods will
 * automagically get wired up at runtime with hibernate implementations.
 */
interface UserDAO {
	fun findById(id: Long): Optional<User>
	fun findByEmail(email: String): Optional<User>
}