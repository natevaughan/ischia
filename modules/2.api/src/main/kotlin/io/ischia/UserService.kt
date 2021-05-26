package io.ischia

import io.ischia.domain.User

interface UserService {
	/**
	 * @throws NotFoundException if user is not found
	 */
	fun findById(id: Long): User
	/**
	 * @throws NotFoundException if user is not found
	 */
	fun findByEmail(email: String): User
}