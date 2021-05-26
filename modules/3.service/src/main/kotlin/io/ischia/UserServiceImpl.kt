package io.ischia

import io.ischia.domain.User
import io.ischia.domain.UserDAO
import javax.inject.Named

@Named("userService")
class UserServiceImpl(private val userDAO: UserDAO): UserService {
	override fun findById(id: Long): User {
		return userDAO.findById(id).orElseThrow { NotFoundException("User not found with id $id") }
	}

	override fun findByEmail(email: String): User {
		return userDAO.findByEmail(email).orElseThrow { NotFoundException("User not found by email") }
	}
}